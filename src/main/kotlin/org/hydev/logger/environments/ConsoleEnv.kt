package org.hydev.logger.environments

import org.hydev.logger.withoutFormat

class ConsoleEnv: LogEnvironment()
{
    override fun logRaw(message: String) = println(message.withoutFormat())
}
