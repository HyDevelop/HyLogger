package cc.moecraft.logger;

import cc.moecraft.yaml.utils.FileUtils;
import lombok.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 此类由 Hykilpikonna 在 2018/05/27 创建!
 * Created by Hykilpikonna on 2018/05/27!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data
public class FileLogger
{
    public PrintWriter fileWriter;
    public File file;
    public DebugLogger logger;
    private long lastLogTime = System.currentTimeMillis();

    public FileLogger(File file, DebugLogger logger) throws IOException
    {
        this.file = file;
        if (!file.exists())
        {
            FileUtils.createFile(file);
        }
        this.fileWriter = new PrintWriter(file);
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            logger.log(AnsiColor.GREEN + "检测到进程关闭... 正在保存Log...");
            fileWriter.flush();
            fileWriter.close();
        }));
    }

    public void log(String message)
    {
        fileWriter.write(replaceAllColors(message) + "\n");
        if (System.currentTimeMillis() - lastLogTime > 5000)
        {
            lastLogTime = System.currentTimeMillis();
            fileWriter.flush();
        }
    }

    public String replaceAllColors(String original)
    {
        return original.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    public class FileSaverThread extends Thread
    {

    }
}
