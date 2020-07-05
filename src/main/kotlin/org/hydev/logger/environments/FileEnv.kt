package org.hydev.logger.environments

import org.fusesource.jansi.AnsiConsole
import org.hydev.logger.HyLoggerConfig.fileFormat
import org.hydev.logger.HyLoggerConfig.fileTimePattern
import org.hydev.logger.format.AnsiColor
import org.hydev.logger.now
import org.hydev.logger.withoutFormat
import java.io.File
import java.io.PrintWriter

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

    constructor(path: String, name: String) :
        this(File(File(path), fileFormat.replace("{name}", name).replace("{time}", fileTimePattern.now())))

    override fun logRaw(message: String)
    {
        fileWriter.write(message.withoutFormat())
        fileWriter.flush()
    }
}
