package org.hydev.logger.environments

import org.hydev.logger.HyLoggerConfig.timePattern
import org.hydev.logger.now

/**
 * 此类由 Hykilpikonna 在 2018/07/02 创建!
 * Created by Hykilpikonna on 2018/07/02!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
abstract class LogEnvironment
{
    abstract fun logRaw(message: String)

    fun log(format: String, prefix: String, message: String)
    {
        logRaw(
            replaceVariables(
                format,
                prefix,
                message
            )
        )
    }

    fun log(format: String, prefix: String, stackTraceElement: StackTraceElement)
    {
        var format = format
        format = replaceBasicVariables(
            format,
            prefix
        )
        format = format.replace("{message}", stackTraceElement.toString())
        logRaw(format)
    }

    companion object
    {
        fun replaceVariables(format: String, prefix: String, message: String): String
        {
            return replaceMessage(
                replaceBasicVariables(
                    format,
                    prefix
                ),
                message
            )
        }

        private fun replaceMessage(basicFormat: String, message: String): String
        {
            return basicFormat.replace("{message}", message)
        }

        private fun replaceBasicVariables(format: String, prefix: String): String
        {
            var stackTrace: StackTraceElement? = null
            val elements = Thread.currentThread().stackTrace
            for (i in 5 until elements.size)
            {
                if (!elements[i].className.startsWith("cc.moecraft.logger")) stackTrace = elements[i]
            }
            if (stackTrace == null) stackTrace = Thread.currentThread().stackTrace[0]
            val stClass = stackTrace!!.className
            val stMethod = stackTrace.methodName
            val stLine = stackTrace.lineNumber.toString() + ""
            val stFull = "$stClass.$stMethod:$stLine"
            return format
                .replace("{time}", timePattern.now())
                .replace("{prefix}", prefix)
                .replace("{st.class}", stClass)
                .replace("{st.method}", stMethod)
                .replace("{st.line}", stLine)
                .replace("{st.full}", stFull)
        }
    }
}
