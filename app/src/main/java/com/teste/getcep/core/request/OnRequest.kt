package com.teste.getcep.core.request

import com.teste.getcep.core.fuctions.Result
import com.teste.getcep.data.model.error.FailureError
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicReference

interface OnRequest {

    suspend fun <T> onRequest(
        request: Deferred<T>,
        result: Result<T?, FailureError>.() -> Unit
    )
}

interface CallApi {
    suspend fun <T> safeCallApi(
        dispatcher: CoroutineDispatcher,
        api: suspend () -> T
    ): Result<T, FailureError>
}

class DisposableTaskJob {
    private val jobs by lazy { mutableSetOf<Job>() }

    fun add(job: Job) {
        jobs.add(job)
    }

    fun clear() {
        jobs.run {
            forEach { it.cancel() }
            clear()
        }
    }
}

class ControlledRunner<T> {
    private val activeTask = AtomicReference<Deferred<T>?>(null)

    suspend fun cancelPreviousThenRun(block: suspend () -> T): T {
        activeTask.get()?.cancelAndJoin()

        return coroutineScope {
            val newTask = async(start = CoroutineStart.LAZY) {
                block()
            }

            newTask.invokeOnCompletion {
                activeTask.compareAndSet(newTask, null)
            }

            val result: T

            while (true) {
                if (!activeTask.compareAndSet(null, newTask)) {
                    activeTask.get()?.cancelAndJoin()
                    yield()
                } else {
                    result = newTask.await()
                    break
                }
            }
            result
        }
    }
}