package org.hydev.logger.environments

import java.io.File

/**
 * 此类由 Hykilpikonna 在 2018/07/04 创建!
 * Created by Hykilpikonna on 2018/07/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
class FileColoredEnv: FileEnv
{
    constructor(file: File) : super(file)
    constructor(path: String, name: String) : super(path, name)

    override fun logRaw(message: String)
    {
        fileWriter.write(message)
        if (System.currentTimeMillis() - lastLogTime > 5000)
        {
            lastLogTime = System.currentTimeMillis()
            fileWriter.flush()
        }
    }
}
