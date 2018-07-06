package cc.moecraft.logger.coloring;

import cc.moecraft.logger.format.AnsiConstants;

import java.awt.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AnsiRGB extends Color
{
    public AnsiRGB(int r, int g, int b)
    {
        super(r, g, b);
    }

    public AnsiRGB(int r, int g, int b, int a)
    {
        super(r, g, b, a);
    }

    public AnsiRGB(int rgb)
    {
        super(rgb);
    }

    public AnsiRGB(int rgba, boolean hasalpha)
    {
        super(rgba, hasalpha);
    }

    public AnsiRGB(float r, float g, float b)
    {
        super(r, g, b);
    }

    public AnsiRGB(float r, float g, float b, float a)
    {
        super(r, g, b, a);
    }

    public AnsiRGB(Color color)
    {
        super(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
}
