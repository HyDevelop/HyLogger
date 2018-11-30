package cc.moecraft.errortest;

import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;
import cc.moecraft.logger.environments.ColorSupportLevel;
import cc.moecraft.logger.environments.ConsoleColoredEnv;
import cc.moecraft.logger.environments.FileEnv;

/**
 * 此类由 Hykilpikonna 在 2018/08/18 创建!
 * Created by Hykilpikonna on 2018/08/18!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@SuppressWarnings("WeakerAccess")
public class ErrorTest
{
    public static void main(String[] args)
    {
        LoggerInstanceManager loggerInstanceManager = new LoggerInstanceManager(
                new ConsoleColoredEnv(ColorSupportLevel.FORCED), new FileEnv("logs", "log"));
        HyLogger logger = loggerInstanceManager.getLoggerInstance("LoggerTest", true);

        try
        {
            thisIsAMethod();
        }
        catch (Exception e)
        {
            Exception e1 = new Exception(e);
            Exception e2 = new Exception("一个测试报错Cause消息w", e1);
            Exception e3 = new Exception("一个测试报错消息w", e2);
            logger.error(e3);
        }
    }

    public static void thisIsAMethod()
    {
        thisIsAnotherMethod();
    }

    public static void thisIsAnotherMethod()
    {
        fillingTheStackTrace();
    }

    public static void fillingTheStackTrace()
    {
        divideByZero();
    }

    public static void divideByZero()
    {
        int i = 1 / 0;
    }
}
