package com.kotlin.demo.project.clean.mvp.core

import java.util.concurrent.*

interface ThreadExecutor : Executor

class JobExecutor() : ThreadExecutor {

    private val threadPoolkExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        3,
        5,
        10,
        TimeUnit.SECONDS,
        LinkedBlockingDeque()
    )

    override fun execute(command: Runnable?) {
        threadPoolkExecutor.execute(command)
    }
}

class JobThreadFactory(private val counter: Int = 0): ThreadFactory {
    override fun newThread(r: Runnable?): Thread = Thread (
        r, "android_${counter.inc()}"
    )
}