package org.hydev.logger

import org.fusesource.jansi.AnsiConsole
import org.hydev.logger.HyLogger.Companion.general
import org.hydev.logger.appenders.Appender
import org.hydev.logger.appenders.ColorCompatibility
import org.hydev.logger.appenders.ColorCompatibility.PRESET_ONLY
import org.hydev.logger.appenders.ConsoleAppender
import org.hydev.logger.coloring.AnsiColorMode
import org.hydev.logger.utils.HyPrintStream
import java.io.PrintStream

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

    var out: PrintStream = System.out

    val _originalOut: PrintStream = System.out
    val _originalErr: PrintStream = System.err

    /**
     * Make System.out.println() go through HyLogger.general.log()
     */
    fun installSysOut()
    {
        System.setOut(HyPrintStream(_originalOut) { general.log(it) })
    }

    /**
     * Make System.err.println() go through HyLogger.general.error()
     */
    fun installSysErr()
    {
        System.setErr(HyPrintStream(_originalErr) { general.error(it) })
    }

    /**
     * Enable windows cmd color compatibility
     */
    fun enableWindowsCmdCompatibility()
    {
        out = AnsiConsole.out
        colorCompatibility = PRESET_ONLY
    }
}
