package cc.moecraft.tests;

import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;
import cc.moecraft.logger.coloring.GradientPresets;
import cc.moecraft.logger.coloring.MultiPointLinearGradient;
import cc.moecraft.logger.environments.ColorSupportLevel;
import cc.moecraft.logger.environments.ConsoleColoredEnv;
import cc.moecraft.logger.environments.FileEnv;
import cc.moecraft.logger.text.Paragraph;
import cc.moecraft.logger.utils.TextColoringUtil;
import org.fusesource.jansi.AnsiConsole;

import java.awt.*;

import static cc.moecraft.logger.coloring.GradientPresets.*;
import static cc.moecraft.logger.coloring.GradientPresets.BPR;
import static cc.moecraft.logger.coloring.GradientPresets.RAINBOW;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class LoggerTest
{
    private static LoggerInstanceManager loggerInstanceManager;

    public static void main(String[] args)
    {
        long time = System.nanoTime();

        loggerInstanceManager = new LoggerInstanceManager(new ConsoleColoredEnv(ColorSupportLevel.FORCED), new FileEnv("logs", "log"));

        HyLogger logger = loggerInstanceManager.getLoggerInstance("LoggerTest", true);

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        logger.log("一条测试Log消息");

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        logger.debug("一条测试Debug消息");

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        logger.error("一条测试Error消息");

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        logger.warning("一条测试Warning消息");

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        logger.fancy.logRAINBOW("测试随机颜色消息\n");

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        logger.fancy.logGradient("测试渐变从深蓝到浅蓝", Color.BLUE, Color.CYAN);

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        logger.fancy.logGradient("测试渐变从橘色到浅蓝", Color.ORANGE, Color.CYAN);

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        logger.fancy.logGradient("测试黄绿渐变到天蓝",
                new Color(0, 242, 96),
                new Color(80, 161, 230));

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        logger.fancy.logGradient("测试橙色渐变到粉色",
                new Color(255, 140, 0),
                new Color(255, 0, 128));

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        logger.fancy.logGradient("##############测试彩虹多点渐变预设##############", RAINBOW);

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        logger.fancy.logGradient("##############测试蓝到紫到红多点渐变##############",BPR);

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        MultiPointLinearGradient gradient = BOP;

        {
            logger.log("测试Paragraph斜向线性渐变 #1:");

            Paragraph paragraph = new Paragraph("" +
                    "┬ ┬┬ ┬┬  ┌─┐┌─┐┌─┐┌─┐┬─┐",
                    "├─┤└┬┘│  │ ││ ┬│ ┬├┤ ├┬┘",
                    "┴ ┴ ┴ ┴─┘└─┘└─┘└─┘└─┘┴└─"
            );

            Paragraph newParagraph = TextColoringUtil.getGradientParagraph(paragraph.toCharArray(), BPR, 15);

            newParagraph.logTo(logger);
        }

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        {
            logger.log("测试Paragraph斜向线性渐变 #2:");

            Paragraph paragraph = new Paragraph("" +
                    "    __  __      __                               ",
                    "   / / / /_  __/ /   ____  ____ _____ ____  _____",
                    "  / /_/ / / / / /   / __ \\/ __ `/ __ `/ _ \\/ ___/",
                    " / __  / /_/ / /___/ /_/ / /_/ / /_/ /  __/ /    ",
                    "/_/ /_/\\__, /_____/\\____/\\__, /\\__, /\\___/_/     ",
                    "      /____/            /____//____/             "
            );

            Paragraph newParagraph = TextColoringUtil.getGradientParagraph(paragraph.toCharArray(), gradient, 60);

            newParagraph.logTo(logger);
        }

        logger.log(((double) System.nanoTime() - time) / 1000000d + "ms"); time = System.nanoTime();

        // 写完多点之后测试:
        //   #JShine: new Color(18, 194, 233), new Color(196, 113, 237), new Color(246, 79, 89)
        //   #黄绿橙粉: new Color(64, 224, 208), new Color(255, 140, 0), new Color(255, 0, 128)

        AnsiConsole.systemInstall();
    }
}
