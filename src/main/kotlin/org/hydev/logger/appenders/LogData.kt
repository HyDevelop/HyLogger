package org.hydev.logger.appenders

import org.hydev.logger.LogLevel

/**
 * All the data required to format a log entry
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-07-05 19:43
 */
data class LogData(
    val level: LogLevel,
    val prefix: String,
    val msg: String,
    val fqcn: String
)
