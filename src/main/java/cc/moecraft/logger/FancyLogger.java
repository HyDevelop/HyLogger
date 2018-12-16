package cc.moecraft.logger;

import cc.moecraft.logger.coloring.AnsiColorMode;
import cc.moecraft.logger.coloring.MultiPointLinearGradient;
import cc.moecraft.logger.format.AnsiConstants;
import cc.moecraft.logger.text.Paragraph;
import cc.moecraft.logger.utils.TextColoringUtil;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Random;

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class FancyLogger
{
    private HyLogger logger;
    
    @Setter @Getter
    private AnsiColorMode colorMode = AnsiColorMode.COLOR_RGB;

    protected FancyLogger(HyLogger logger)
    {
        this.logger = logger;
    }

    @Deprecated
    public void logRAINBOW(String message)
    {
        StringBuilder newMessage = new StringBuilder();

        Random random = new Random();

        for (char c : message.toCharArray())
        {
            newMessage.append(AnsiConstants.colors.get(random.nextInt(AnsiConstants.colors.size()))).append(c);
        }

        logger.log(newMessage.toString());
    }

    public void logGradient(String message, Color color1, Color color2, Color ... colors)
    {
        logger.log(new TextColoringUtil(message, colorMode).getGradientText(color1, color2, colors));
    }

    public void logGradient(String message, MultiPointLinearGradient gradient)
    {
        logger.log(new TextColoringUtil(message, colorMode).getGradientText(gradient));
    }

    public void logGradient(Paragraph message, MultiPointLinearGradient gradient, int degrees)
    {
        logger.log(TextColoringUtil.getGradientParagraph(colorMode, message.toCharArray(), gradient, degrees));
    }

    public void logGradient(Paragraph message, MultiPointLinearGradient gradient)
    {
        logger.log(TextColoringUtil.getGradientParagraph(colorMode, message.toCharArray(), gradient, 0));
    }
}
