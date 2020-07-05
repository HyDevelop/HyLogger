package org.hydev.logger.environments

import org.hydev.logger.withoutFormat

/**
 * 此类由 Hykilpikonna 在 2018/07/03 创建!
 * Created by Hykilpikonna on 2018/07/03!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
class ConsoleEnv: LogEnvironment()
{
    override fun logRaw(message: String)
    {
        println(message.withoutFormat())
    }
}
