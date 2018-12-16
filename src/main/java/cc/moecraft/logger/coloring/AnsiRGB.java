package cc.moecraft.logger.coloring;

import lombok.*;

import java.awt.*;

import static cc.moecraft.logger.format.AnsiConstants.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@SuppressWarnings("WeakerAccess")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnsiRGB
{
    private Color foreground;
    private Color background;
    private AnsiColorMode colorMode = AnsiColorMode.COLOR_RGB;
    
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
        this();
        setForeground(color);
    }
    
    /**
     * Construct an AnsiRGB object with R G B values.
     *
     * @param r Red
     * @param g Green
     * @param b Blue
     */
    public AnsiRGB(int r, int g, int b)
    {
        this(new Color(r, g, b));
    }
    
    /**
     * Get ANSI color code.
     *
     * @return ANSI color code.
     */
    @Override
    public String toString()
    {
        String result = ESC_PREFIX;
        
        if (foreground != null) result += FOREGROUND + colorMode.format(foreground);
        if (background != null) result += BACKGROUND + colorMode.format(background);
        
        return result + SUFFIX;
    }
}
