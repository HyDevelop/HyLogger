package org.hydev.logger.environments

class ConsoleColoredEnv(val csl: ColorSupportLevel) : LogEnvironment()
{
    override fun logRaw(message: String) = csl.log(message)
}
