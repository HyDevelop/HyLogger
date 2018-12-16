package cc.moecraft.logger.coloring;

import cc.moecraft.logger.format.AnsiConstants;

import java.awt.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AnsiRGB extends Color
{
    public AnsiRGB(int r, int g, int b)
    {
        super(r, g, b);
    }

    public AnsiRGB(int rgb)
    {
        super(rgb);
    }

    public AnsiRGB(float r, float g, float b)
    {
        super(r, g, b);
    }

    public AnsiRGB(Color color)
    {
        this(color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public String toString()
    {
        return toAnsi();
    }

    public String toAnsi()
    {
        return toAnsi(getRed(), getGreen(), getBlue());
    }

    /**
     * 用RGB获取一个ANSI码
     * @param r 红
     * @param g 绿
     * @param b 蓝
     * @return ANSI颜色码
     */
    public static String toAnsi(int r, int g, int b)
    {
        return String.format(AnsiConstants.RGB_FORMAT, r, g, b);
    }
}
