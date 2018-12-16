package cc.moecraft.logger.coloring;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;

/**
 * 此类由 Hykilpikonna 在 2018/12/16 创建!
 * Created by Hykilpikonna on 2018/12/16!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@AllArgsConstructor
@Getter
public enum AnsiColorMode
{
    COLOR_RGB((r, g, b) -> String.format("2;%s;%s;%s", r, g, b)),
    COLOR_8BIT((r, g, b) -> String.format("5;%s", (r * 6 / 256) * 36 + (g * 6 / 256) * 6 + (b * 6 / 256)));
    
    private final AnsiColorFormatter formatter;
    
    private interface AnsiColorFormatter
    {
        String format(int r, int g, int b);
    }
    
    /**
     * Format a color object into this color format.
     *
     * @param color Color object
     * @return Formatted string.
     */
    public String format(Color color)
    {
        return format(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    /**
     * Format RGB values into this color format.
     *
     * @param r Red
     * @param g Green
     * @param b Blue
     * @return Formatted string.
     */
    private String format(int r, int g, int b)
    {
        return formatter.format(r, g, b);
    }
}
