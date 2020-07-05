package cc.moecraft.logger

import cc.moecraft.logger.HyLoggerConfig.debug
import cc.moecraft.logger.HyLoggerConfig.environments
import cc.moecraft.logger.HyLoggerConfig.formats
import cc.moecraft.logger.LogLevel.*
import cc.moecraft.logger.utils.FormatUtils

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
    fun log(level: LogLevel, format: String, vararg args: Any)
    {
        log(level, FormatUtils.resolve(format, *args))
    }

    /**
     * Log message with System.printf() format.
     *
     * @param level Level
     * @param format System.printf() format.
     * @param args Arguments.
     */
    fun logf(level: LogLevel, format: String?, vararg args: Any)
    {
        log(level, String.format(format!!, *args))
    }

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
    fun newLine()
    {
        log(LOG, "")
    }

    /**
     * Returns true when the level is debug and the debug option is false.
     *
     * @param level Level
     * @return True when the level is debug and the debug option is false.
     */
    fun checkDebug(level: LogLevel): Boolean
    {
        return level == DEBUG && !debug
    }

    fun log(message: String)
    {
        log(LOG, message)
    }

    fun debug(message: String)
    {
        log(DEBUG, message)
    }

    fun error(message: String)
    {
        log(ERROR, message)
    }

    fun warning(message: String)
    {
        log(WARNING, message)
    }

    fun log(format: String, vararg message: Any) = log(LOG, format, *message)

    fun debug(format: String, vararg message: Any)
    {
        log(DEBUG, format, *message)
    }

    fun error(format: String, vararg message: Any)
    {
        log(ERROR, format, *message)
    }

    fun warning(format: String, vararg message: Any)
    {
        log(WARNING, format, *message)
    }

    fun logf(format: String, vararg message: Any)
    {
        logf(LOG, format, *message)
    }

    fun debugf(format: String, vararg message: Any)
    {
        logf(DEBUG, format, *message)
    }

    fun errorf(format: String, vararg message: Any)
    {
        logf(ERROR, format, *message)
    }

    fun warningf(format: String, vararg message: Any)
    {
        logf(WARNING, format, *message)
    }

    fun error(message: String, throwable: Throwable)
    {
        error(message)
        error(throwable)
    }
}
