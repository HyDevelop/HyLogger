package org.hydev.logger

import org.hydev.logger.HyLoggerConfig.debug
import org.hydev.logger.HyLoggerConfig.environments
import org.hydev.logger.HyLoggerConfig.formats
import org.hydev.logger.LogLevel.*
import org.hydev.logger.utils.FormatUtils.resolve

class HyLogger(val prefix: String)
{
    val fancy = FancyLogger(this)
    val timing = TimingLogger(this)

    /**
     * Log a message.
     *
     * @param level Level
     * @param message Message
     */
    fun log(level: LogLevel, message: String)
    {
        if (checkDebug(level)) return
        environments.forEach { it.log(formats[level.id], prefix, message) }
    }

    /**
     * Log message with Slf4J format.
     *
     * @param level Level
     * @param format Slf4J Format
     * @param args Arguments
     */
    fun log(level: LogLevel, format: String, vararg args: Any) = log(level, resolve(format, *args))

    /**
     * Log message with System.printf() format.
     *
     * @param level Level
     * @param format System.printf() format.
     * @param args Arguments.
     */
    fun logf(level: LogLevel, format: String, vararg args: Any) = log(level, String.format(format, *args))

    /**
     * Log a stack trace element.
     *
     * @param level Level
     * @param stackTraceElement Stack trace element.
     */
    fun log(level: LogLevel, stackTraceElement: StackTraceElement)
    {
        if (checkDebug(level)) return
        environments.forEach { it.log(formats[level.id], prefix, stackTraceElement) }
    }

    /**
     * Log an empty line.
     */
    fun newLine() = log(LOG, "")

    /**
     * Returns true when the level is debug and the debug option is false.
     *
     * @param level Level
     * @return True when the level is debug and the debug option is false.
     */
    fun checkDebug(level: LogLevel): Boolean = level == DEBUG && !debug

    fun log(message: String) = log(LOG, message)
    fun debug(message: String) = log(DEBUG, message)
    fun error(message: String) = log(ERROR, message)
    fun warning(message: String) = log(WARNING, message)

    fun log(format: String, vararg args: Any) = log(LOG, format, *args)
    fun debug(format: String, vararg args: Any) = log(DEBUG, format, *args)
    fun error(format: String, vararg args: Any) = log(ERROR, format, *args)
    fun warning(format: String, vararg args: Any) = log(WARNING, format, *args)

    fun logf(format: String, vararg args: Any) = logf(LOG, format, *args)
    fun debugf(format: String, vararg args: Any) = logf(DEBUG, format, *args)
    fun errorf(format: String, vararg args: Any) = logf(ERROR, format, *args)
    fun warningf(format: String, vararg args: Any) = logf(WARNING, format, *args)
}
