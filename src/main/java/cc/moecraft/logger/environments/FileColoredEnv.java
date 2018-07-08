package cc.moecraft.logger.environments;

import java.io.File;

/**
 * 此类由 Hykilpikonna 在 2018/07/04 创建!
 * Created by Hykilpikonna on 2018/07/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class FileColoredEnv extends FileEnv
{
    public FileColoredEnv(File file)
    {
        super(file);
    }

    public FileColoredEnv(String filePath, String fileName)
    {
        super(filePath, fileName);
    }

    @Override
    public void logRaw(String message)
    {
        getFileWriter().write(message + "\n");
        if (System.currentTimeMillis() - lastLogTime > 5000)
        {
            lastLogTime = System.currentTimeMillis();
            getFileWriter().flush();
        }
    }
}
