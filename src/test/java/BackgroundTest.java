import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;
import cc.moecraft.logger.environments.ColorSupportLevel;
import cc.moecraft.logger.environments.ConsoleColoredEnv;
import cc.moecraft.logger.format.AnsiColor;

/**
 * 此类由 Hykilpikonna 在 2018/12/16 创建!
 * Created by Hykilpikonna on 2018/12/16!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class BackgroundTest
{
    public static void main(String[] args)
    {
        LoggerInstanceManager lim = new LoggerInstanceManager(new ConsoleColoredEnv(ColorSupportLevel.FORCED));
        HyLogger logger = lim.getLoggerInstance("BackgroundTest", true);
    
        logger.log(AnsiColor.RED.getBackground() + AnsiColor.GREEN + "Hello world!");
    }
}
