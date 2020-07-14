package org.hydev.logger.appenders

import org.hydev.logger.HyLoggerConfig.colorCompatibility
import org.hydev.logger.HyLoggerConfig.timePattern
import org.hydev.logger.LogLevel.*
import org.hydev.logger.format.AnsiColor.*
import org.hydev.logger.now
import org.hydev.logger.parseFormats

class ConsoleAppender() : Appender()
{
    init
    {
        // Create formatter
        val defaultFormat = "&f[&5%s&f] [&1%s&f] [%s&f] %s&r".parseFormats()
        val fqcnFormat = "&f[&5%s&f] [&1%s&f] [%s&f(&e%s&f)] %s&r".parseFormats()

        formatter =
        {
            val time = timePattern.now()

            when (it.level)
            {
                LOG -> defaultFormat.format(time, it.prefix, "${GREEN}INFO", "$RESET${it.msg}")
                WARNING -> defaultFormat.format(time, it.prefix, "${RED}WARNING", "$YELLOW${it.msg}")
                DEBUG -> fqcnFormat.format(time, it.prefix, "${CYAN}DEBUG", it.fqcn, "$CYAN${it.msg}")
                ERROR -> fqcnFormat.format(time, it.prefix, "${RED}ERROR", it.fqcn, "$RED${it.msg}")
            }
        }
    }

    override fun logRaw(message: String) = colorCompatibility.log(message)
}
