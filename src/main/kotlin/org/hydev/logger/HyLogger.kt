package org.hydev.logger

import org.hydev.logger.HyLoggerConfig.appenders
import org.hydev.logger.HyLoggerConfig.debug
import org.hydev.logger.LogLevel.*
import org.hydev.logger.appenders.LogData
import org.hydev.logger.utils.FormatUtils.resolve

class HyLogger(val prefix: String)
{
    val fancy = FancyLogger(this)
    val timing = TimingLogger(this)

    /**
     * Log a message.
     */
    private fun log(level: LogLevel, message: String)
    {
        if (level == DEBUG && !debug) return

        // Find stack trace
        val stack = Thread.currentThread().stackTrace
            .first { s -> !s.className.startsWith("org.hydev.logger") }
        val fqcn = "${stack.className}.${stack.methodName}:${stack.lineNumber}"

        message.lines().forEach { line ->
            appenders.forEach { it.log(LogData(level, prefix, line, fqcn)) }
        }
    }

    /**
     * Log message with Slf4J format.
     */
    private fun log(level: LogLevel, format: String, vararg args: Any) = log(level, resolve(format, *args))

    /**
     * Log message with System.printf() format.
     */
    private fun logf(level: LogLevel, format: String, vararg args: Any) = log(level, String.format(format, *args))

    /**
     * Log an empty line.
     */
    fun newLine() = log(LOG, "")

    fun log(message: Any) = log(LOG, message.toString())
    fun debug(message: Any) = log(DEBUG, message.toString())
    fun error(message: Any) = log(ERROR, message.toString())
    fun warning(message: Any) = log(WARNING, message.toString())

    fun log(format: String, vararg args: Any) = log(LOG, format, *args)
    fun debug(format: String, vararg args: Any) = log(DEBUG, format, *args)
    fun error(format: String, vararg args: Any) = log(ERROR, format, *args)
    fun warning(format: String, vararg args: Any) = log(WARNING, format, *args)

    fun logf(format: String, vararg args: Any) = logf(LOG, format, *args)
    fun debugf(format: String, vararg args: Any) = logf(DEBUG, format, *args)
    fun errorf(format: String, vararg args: Any) = logf(ERROR, format, *args)
    fun warningf(format: String, vararg args: Any) = logf(WARNING, format, *args)
}
