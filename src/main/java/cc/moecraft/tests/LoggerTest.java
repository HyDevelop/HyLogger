package cc.moecraft.tests;

import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;
import cc.moecraft.logger.TimingLogger;
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
        TimingLogger timing = new TimingLogger();

        loggerInstanceManager = new LoggerInstanceManager(new ConsoleColoredEnv(ColorSupportLevel.FORCED), new FileEnv("logs", "log"));

        HyLogger logger = loggerInstanceManager.getLoggerInstance("LoggerTest", true);

        timing.timeAndReset(logger);

        logger.log("一条测试Log消息");

        timing.timeAndReset(logger);

        logger.debug("一条测试Debug消息");

        timing.timeAndReset(logger);

        logger.error("一条测试Error消息");

        timing.timeAndReset(logger);

        logger.warning("一条测试Warning消息");

        timing.timeAndReset(logger);

        logger.fancy.logRAINBOW("测试随机颜色消息\n");

        timing.timeAndReset(logger);

        logger.fancy.logGradient("测试渐变从深蓝到浅蓝", Color.BLUE, Color.CYAN);

        timing.timeAndReset(logger);

        logger.fancy.logGradient("测试渐变从橘色到浅蓝", Color.ORANGE, Color.CYAN);

        timing.timeAndReset(logger);

        logger.fancy.logGradient("测试黄绿渐变到天蓝",
                new Color(0, 242, 96),
                new Color(80, 161, 230));

        timing.timeAndReset(logger);

        logger.fancy.logGradient("测试橙色渐变到粉色",
                new Color(255, 140, 0),
                new Color(255, 0, 128));

        timing.timeAndReset(logger);

        logger.fancy.logGradient("##############测试彩虹多点渐变预设##############", RAINBOW);

        timing.timeAndReset(logger);

        logger.fancy.logGradient("##############测试蓝到紫到红多点渐变##############",BPR);

        timing.timeAndReset(logger);

        {
            logger.log("测试Paragraph斜向线性渐变 #1:");

            Paragraph paragraph = new Paragraph("" +
                    "┬ ┬┬ ┬┬  ┌─┐┌─┐┌─┐┌─┐┬─┐",
                    "├─┤└┬┘│  │ ││ ┬│ ┬├┤ ├┬┘",
                    "┴ ┴ ┴ ┴─┘└─┘└─┘└─┘└─┘┴└─"
            );

            logger.fancy.logGradient(paragraph, BPR, 15);
        }

        timing.timeAndReset(logger);

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

            logger.fancy.logGradient(paragraph, BOP, 60);
        }

        timing.timeAndReset(logger);
    }
}
