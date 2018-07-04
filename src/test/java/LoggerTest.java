import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;
import cc.moecraft.logger.environments.ConsoleColoredEnv;
import cc.moecraft.logger.environments.FileEnv;

import java.io.IOException;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class LoggerTest
{
    private static LoggerInstanceManager loggerInstanceManager;

    public static void main(String[] args) throws InterruptedException, IOException
    {
        loggerInstanceManager = new LoggerInstanceManager(new ConsoleColoredEnv(), new FileEnv("logs", "log"));

        HyLogger logger = loggerInstanceManager.getLoggerInstance("LoggerTest", true);

        logger.log("一条测试Log消息");
        logger.debug("一条测试Debug消息");
        logger.error("一条测试Error消息");
        logger.warning("一条测试Warning消息");
        logger.logRAINBOW("一条测试彩虹消息wwwwww");
    }
}
