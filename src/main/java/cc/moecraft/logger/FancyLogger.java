package cc.moecraft.logger;

import cc.moecraft.logger.format.AnsiConstants;
import cc.moecraft.logger.utils.TextColoringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class FancyLogger
{
    private HyLogger logger;

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

    public void logGradient(String message, Color color1, Color color2)
    {
        message = TextColoringUtil.getGradientText(message, color1, color2);
        logger.log(message);
    }
}
