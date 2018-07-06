package cc.moecraft.tests;

import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;
import cc.moecraft.logger.environments.ConsoleColoredEnv;
import cc.moecraft.logger.environments.FileEnv;
import org.fusesource.jansi.AnsiConsole;

import java.awt.*;
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

    public static void main(String[] args)
    {
        loggerInstanceManager = new LoggerInstanceManager(new ConsoleColoredEnv(), new FileEnv("logs", "log"));

        HyLogger logger = loggerInstanceManager.getLoggerInstance("LoggerTest", true);

        logger.log("一条测试Log消息");
        logger.debug("一条测试Debug消息");
        logger.error("一条测试Error消息");
        logger.warning("一条测试Warning消息");

        logger.fancy.logRAINBOW("测试随机颜色消息\n");
        logger.fancy.logGradient("测试渐变从深蓝到浅蓝\n", Color.BLUE, Color.CYAN);
        logger.fancy.logGradient("测试渐变从橘色到浅蓝\n", Color.ORANGE, Color.CYAN);

        logger.fancy.logGradient("测试黄绿渐变到天蓝\n",
                new Color(0, 242, 96),
                new Color(80, 161, 230));

        logger.fancy.logGradient("测试橙色渐变到粉色\n",
                new Color(255, 140, 0),
                new Color(255, 0, 128));

        // 写完多点之后测试:
        //   #JShine: new Color(18, 194, 233), new Color(196, 113, 237), new Color(246, 79, 89)
        //   #黄绿橙粉: new Color(64, 224, 208), new Color(255, 140, 0), new Color(255, 0, 128)

        AnsiConsole.systemInstall();
    }
}
