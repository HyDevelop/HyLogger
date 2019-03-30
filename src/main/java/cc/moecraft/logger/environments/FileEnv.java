package cc.moecraft.logger.environments;

import cc.moecraft.logger.format.AnsiColor;
import cc.moecraft.logger.utils.FileUtils;
import lombok.Getter;
import org.fusesource.jansi.AnsiConsole;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static cc.moecraft.logger.utils.AnsiUtils.removeFormat;

/**
 * 此类由 Hykilpikonna 在 2018/05/27 创建!
 * Created by Hykilpikonna on 2018/05/27!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Getter
public class FileEnv extends LogEnvironment
{
    private PrintWriter fileWriter;
    private File file;

    long lastLogTime = System.currentTimeMillis();
    
    /**
     * Construct a file logging environment with a file object.
     *
     * @param file File object.
     */
    public FileEnv(File file)
    {
        this.file = file;
        if (!file.exists()) FileUtils.create(file);

        try
        {
            this.fileWriter = new PrintWriter(file);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException("Failed to create file logger:", e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            AnsiConsole.out.println(AnsiColor.GREEN + "检测到进程关闭... 正在保存Log...");
            fileWriter.flush();
            fileWriter.close();
        }));
    }

    public FileEnv(String filePath, String fileName)
    {
        this(getFile(filePath, fileName));
    }

    private static File getFile(String filePath, String fileName)
    {
        if (fileName == null) throw new RuntimeException("Failed to get file:", new NullPointerException());
        if (filePath == null || filePath.isEmpty()) filePath = "./";
        if (!(filePath.endsWith("/") || filePath.endsWith("\\"))) filePath += File.separator;
        return new File(filePath + fileName + "@" + getCurrentTime()
                .replace(":", "-")
                .replace(" ", "-") + ".log");
    }

    @Override
    public void logRaw(String message)
    {
        fileWriter.write(removeFormat(message) + "\n");
        if (System.currentTimeMillis() - lastLogTime > 5000)
        {
            lastLogTime = System.currentTimeMillis();
            fileWriter.flush();
        }
    }
}
