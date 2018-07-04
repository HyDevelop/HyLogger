package cc.moecraft.logger.environments;

import cc.moecraft.logger.format.AnsiColor;
import cc.moecraft.logger.HyLogger;
import cc.moecraft.yaml.utils.FileUtils;
import lombok.Getter;
import org.fusesource.jansi.AnsiConsole;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static cc.moecraft.logger.utils.TimeUtils.getCurrentTime;

/**
 * 此类由 Hykilpikonna 在 2018/05/27 创建!
 * Created by Hykilpikonna on 2018/05/27!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class FileEnv extends LogEnvironment
{
    @Getter
    private PrintWriter fileWriter;

    @Getter
    private File file;

    long lastLogTime = System.currentTimeMillis();

    public FileEnv(File file) throws IOException
    {
        this.file = file;
        if (!file.exists()) FileUtils.createFile(file);

        this.fileWriter = new PrintWriter(file);

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            AnsiConsole.out.println(AnsiColor.GREEN + "检测到进程关闭... 正在保存Log...");
            fileWriter.flush();
            fileWriter.close();
        }));
    }

    public FileEnv(String filePath, String fileName) throws IOException
    {
        this(getFile(filePath, fileName));
    }

    private static File getFile(String filePath, String fileName) throws IOException
    {
        if (fileName == null) throw new IOException();
        if (filePath == null || filePath.isEmpty()) filePath = "./";
        if (!(filePath.endsWith("/") || filePath.endsWith("\\"))) filePath += File.separator;
        return new File(filePath + fileName + "@" + getCurrentTime().replace(":", "-").replace(" ", "-") + ".log");
    }

    @Override
    public void logRaw(String message)
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
}
