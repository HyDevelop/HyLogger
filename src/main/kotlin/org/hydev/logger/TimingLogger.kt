package org.hydev.logger

class TimingLogger(private val logger: HyLogger)
{
    var startTime: Long = 0

    val elapsed: Long
        get() = System.currentTimeMillis() - startTime

    fun time() = apply { logger.log("$elapsed ms") }
    fun reset() = apply { startTime = System.currentTimeMillis() }
}
