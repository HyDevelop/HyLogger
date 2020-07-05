package org.hydev.logger

import org.hydev.logger.coloring.AnsiColorMode
import org.hydev.logger.environments.LogEnvironment
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
    var formats = mutableListOf(
        "&f[&5{time}&f] [&1{prefix}&f] [&aINFO&f] &r{message}&r".parseFormats(),
        "&f[&5{time}&f] [&1{prefix}&f] [&bDEBUG&f(&e{st.full}&f)] &b{message}&r".parseFormats(),
        "&f[&5{time}&f] [&1{prefix}&f] [&cERROR&f(&e{st.full}&f)] &c{message}&r".parseFormats(),
        "&f[&5{time}&f] [&1{prefix}&f] [&cWARNING&f] &e{message}&r".parseFormats(),
        "&f[&5{time}&f] [&1{prefix}&f] &e{message}&r".parseFormats())

    val environments: MutableList<LogEnvironment> = ArrayList()

    var debug = false

    var fileTimePattern = "yy-MM-dd_HH-mm".toDatePattern()
    var fileFormat = "log-{name}@{time}.txt"
}
