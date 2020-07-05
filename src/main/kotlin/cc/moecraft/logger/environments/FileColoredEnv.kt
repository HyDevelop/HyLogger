package cc.moecraft.logger.environments

import java.io.File

/**
 * 此类由 Hykilpikonna 在 2018/07/04 创建!
 * Created by Hykilpikonna on 2018/07/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
class FileColoredEnv: FileEnv
{
    constructor(file: File) : super(file)
    {
    }

    constructor(filePath: String, fileName: String?) : super(filePath, fileName)
    {
    }

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
