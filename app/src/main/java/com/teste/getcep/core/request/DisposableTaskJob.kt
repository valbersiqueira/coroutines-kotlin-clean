package com.teste.getcep.core.request

import kotlinx.coroutines.Job

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