package cc.moecraft.logger.utils;

/**
 * 此类由 Hykilpikonna 在 2018/12/16 创建!
 * Created by Hykilpikonna on 2018/12/16!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class ColorConverter
{
    /**
     * Convert rgb to 8bit
     *
     * @param r Red
     * @param g Green
     * @param b Blue
     * @return 8Bit color
     */
    public static int rgbTo8Bit(int r, int g, int b)
    {
        boolean grayPossible = true;
        boolean gray = false;
        float sep = 42.5f;
        
        while (grayPossible)
        {
            if (r < sep || g < sep || b < sep)
            {
                gray = r < sep && g < sep && b < sep;
                grayPossible = false;
            }
            sep += 42.5;
        }
        
        if (gray)
        {
            return Math.round(232f + (r + g + b) / 33f);
        }
        else
        {
            return 16 + ((int) (r / 256f * 6f)) * 36 + ((int) (g / 256f * 6f)) * 6 + ((int) (b / 256f * 6f));
        }
    }
}
