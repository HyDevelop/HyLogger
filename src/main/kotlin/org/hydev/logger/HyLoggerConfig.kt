package org.hydev.logger

import org.hydev.logger.appenders.Appender
import org.hydev.logger.appenders.ColorCompatibility
import org.hydev.logger.appenders.ConsoleAppender
import org.hydev.logger.coloring.AnsiColorMode

/**
 * Global configuration for the logger
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-07-05 16:23
 */
object HyLoggerConfig
{
    var colorMode = AnsiColorMode.TRUE_COLOR_24BIT
    var colorCompatibility = ColorCompatibility.FORCED

    var timePattern = "yyyy-MM-dd HH:mm:ss".toDatePattern()

    val appenders: MutableList<Appender> = mutableListOf(ConsoleAppender())

    var debug = false

    var fileTimePattern = "yy-MM-dd_HH-mm".toDatePattern()
    var fileFormat = "log-{name}@{time}.csv"

    val out = System.out
}
