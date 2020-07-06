package org.hydev.logger.appenders

abstract class Appender(var formatter: (LogData) -> String = {"Error: Formatter is not programmed"})
{
    abstract fun logRaw(message: String)

    fun log(data: LogData) = logRaw(formatter(data))
}
