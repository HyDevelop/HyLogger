package color;

import cc.moecraft.logger.utils.ColorConverter;

/**
 * 此类由 Hykilpikonna 在 2018/12/16 创建!
 * Created by Hykilpikonna on 2018/12/16!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class GrayScaleTest
{
    public static void main(String[] args)
    {
        for (int r = 0; r < 256; r += 16)
        {
            System.out.print("\033[38;5;" + ColorConverter.rgbTo8Bit(r, r, r) + "m█");
        }
        
        System.out.println("");
        
        for (int r = 0; r < 256; r += 16)
        {
            System.out.print("\033[38;2;" + r + ";" + r + ";" + r + "m█");
        }
    }
}
