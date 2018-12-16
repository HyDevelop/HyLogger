package cc.moecraft.logger.coloring;

import cc.moecraft.logger.format.AnsiConstants;
import lombok.*;

import java.awt.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Getter
@Setter
@AllArgsConstructor
public class AnsiRGB
{
    private int red;
    private int green;
    private int blue;
    
    /**
     * Construct an AnsiRGB object with rgb int (0 - 16777216)
     *
     * @param rgb RGB int value.
     */
    public AnsiRGB(int rgb)
    {
        this(new Color(rgb));
    }
    
    /**
     * Construct an AnsiRGB object with a color object.
     *
     * @param color Color object.
     */
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
