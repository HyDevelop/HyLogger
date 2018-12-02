import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;
import cc.moecraft.logger.environments.ColorSupportLevel;
import cc.moecraft.logger.environments.ConsoleColoredEnv;

/**
 * 此类由 Hykilpikonna 在 2018/12/02 创建!
 * Created by Hykilpikonna on 2018/12/02!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class LogFormatTest
{
    public static void main(String[] args)
    {
        LoggerInstanceManager lim = new LoggerInstanceManager(new ConsoleColoredEnv(ColorSupportLevel.FORCED));
        HyLogger logger = lim.getLoggerInstance("FormatTest", true);

        // Test obtained from:  https://blog.csdn.net/skyupward/article/details/54864522
        logger.log("Set {1,2} differs from {}", 3);
        logger.log("Set \\{} differs from {}", "3");
        logger.log("File name is C:\\\\{}.", "file.zip");
    }
}
