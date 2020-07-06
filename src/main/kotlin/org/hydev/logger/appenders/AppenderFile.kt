package org.hydev.logger.appenders

import org.hydev.logger.HyLoggerConfig.fileFormat
import org.hydev.logger.HyLoggerConfig.fileTimePattern
import org.hydev.logger.now
import org.hydev.logger.withoutFormat
import java.io.File
import java.io.PrintWriter

open class AppenderFile(file: File) : Appender()
{
    var fileWriter: PrintWriter

    init
    {
        // Create formatter (File format defaults to csv)
        formatter =
        {
            val thread = Thread.currentThread()

            listOf<Any>(System.currentTimeMillis(), it.prefix, it.level, it.fqcn, it.msg,
                thread.id, thread.name, thread.priority).joinToString(",", "", "")
        }

        // File
        if (!file.exists()) file.createNewFile()
        fileWriter = file.printWriter()

        // Save on close
        Runtime.getRuntime().addShutdownHook(Thread {
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
