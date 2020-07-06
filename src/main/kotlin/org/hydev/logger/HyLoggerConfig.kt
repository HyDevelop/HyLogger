package org.hydev.logger

import org.hydev.logger.appenders.Appender
import org.hydev.logger.coloring.AnsiColorMode
import java.util.*

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
    var colorMode = AnsiColorMode.COLOR_RGB

    var timePattern = "yyyy-MM-dd HH:mm:ss".toDatePattern()

    val environments: MutableList<Appender> = ArrayList()

    var debug = false

    var fileTimePattern = "yy-MM-dd_HH-mm".toDatePattern()
    var fileFormat = "log-{name}@{time}.txt"
}
