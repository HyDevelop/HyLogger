package org.hydev.logger.environments

import org.hydev.logger.HyLoggerConfig.fileFormat
import org.hydev.logger.HyLoggerConfig.fileTimePattern
import org.hydev.logger.now
import org.hydev.logger.withoutFormat
import java.io.File
import java.io.PrintWriter

open class FileEnv(val file: File) : LogEnvironment()
{
    var fileWriter: PrintWriter
    var lastLogTime = System.currentTimeMillis()

    init
    {
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
