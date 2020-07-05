package org.hydev.logger.environments

import org.hydev.logger.format.AnsiColor
import org.fusesource.jansi.AnsiConsole
import org.hydev.logger.withoutFormat
import java.io.File
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * 此类由 Hykilpikonna 在 2018/05/27 创建!
 * Created by Hykilpikonna on 2018/05/27!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
open class FileEnv(val file: File) : LogEnvironment()
{
    var fileWriter: PrintWriter
    var lastLogTime = System.currentTimeMillis()

    init
    {
        if (!file.exists()) file.createNewFile()
        fileWriter = file.printWriter()

        Runtime.getRuntime().addShutdownHook(Thread {
            AnsiConsole.out.println(AnsiColor.GREEN.toString() + "检测到进程关闭... 正在保存Log...")
            fileWriter.flush()
            fileWriter.close()
        })
    }

    constructor(filePath: String, fileName: String?) : this(
        getFile(
            filePath,
            fileName
        )
    )

    override fun logRaw(message: String)
    {
        fileWriter.write(message.withoutFormat())
        fileWriter.flush()
    }

    companion object
    {
        private fun getFile(filePath: String, fileName: String?): File
        {
            var filePath: String? = filePath
            if (fileName == null) throw RuntimeException("Failed to get file:", NullPointerException())
            if (filePath == null || filePath.isEmpty()) filePath = "./"
            if (!(filePath.endsWith("/") || filePath.endsWith("\\"))) filePath += File.separator
            // TODO: make this format configurable
            return File(
                filePath + fileName + "@" +
                    SimpleDateFormat("yy-MM-dd_HH-mm").format(Calendar.getInstance().time) + ".log"
            )
        }
    }
}
