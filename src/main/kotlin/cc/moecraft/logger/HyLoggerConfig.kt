package cc.moecraft.logger

import cc.moecraft.logger.coloring.AnsiColorMode
import cc.moecraft.logger.environments.LogEnvironment
import org.hydev.toDatePattern
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

    var datePattern = "yyyy-MM-dd HH:mm:ss".toDatePattern()

    var formats = mutableListOf("&f[&5{time}&f] [&1{prefix}&f] [&aINFO&f] &r{message}&r",
        "&f[&5{time}&f] [&1{prefix}&f] [&bDEBUG&f(&e{st.full}&f)] &b{message}&r",
        "&f[&5{time}&f] [&1{prefix}&f] [&cERROR&f(&e{st.full}&f)] &c{message}&r",
        "&f[&5{time}&f] [&1{prefix}&f] [&cWARNING&f] &e{message}&r",
        "&f[&5{time}&f] [&1{prefix}&f] &e{message}&r")

    val environments: MutableList<LogEnvironment> = ArrayList()

    var debug = false
}
