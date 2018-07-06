package cc.moecraft.logger.utils;

import cc.moecraft.logger.coloring.TwoPointGradient;

import java.awt.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class TextColoringUtil
{
    public static String getGradientText(String text, Color color1, Color color2)
    {
        char[] chars = text.toCharArray();
        Color[] colors = new TwoPointGradient(color1, color2).getColors(chars.length);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < chars.length; i++)
        {
            result.append(colors[i].toString()).append(chars[i]);
        }

        return result.toString();
    }
}
