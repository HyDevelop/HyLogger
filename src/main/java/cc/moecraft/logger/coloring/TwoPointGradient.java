package cc.moecraft.logger.coloring;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data @AllArgsConstructor
public class TwoPointGradient
{
    private AnsiRGB color1; // 从什么颜色开始渐变
    private AnsiRGB color2; // 渐变到什么颜色

    public TwoPointGradient(Color color1, Color color2)
    {
        this(new AnsiRGB(color1), new AnsiRGB(color2));
    }

    /**
     * 按数量分割为非自然渐变, 获取一个代表所有渐变色的数组
     * @param amount 数量
     * @return 渐变
     */
    public AnsiRGB[] getColors(int amount)
    {
        AnsiRGB[] colors = new AnsiRGB[amount];

        for (int i = 0; i < amount; i++)
        {
            float ratio = (float) i / (float) amount;

            int resultR = getColorWithRatio(color1.getRed(), color2.getRed(), ratio);
            int resultG = getColorWithRatio(color1.getGreen(), color2.getGreen(), ratio);
            int resultB = getColorWithRatio(color1.getBlue(), color2.getBlue(), ratio);

            AnsiRGB result = new AnsiRGB(resultR, resultG, resultB);

            colors[i] = result;
        }

        return colors;
    }

    /**
     * 按比例计算颜色
     * @param color1 颜色值1
     * @param color2 颜色值2
     * @param ratio 比例
     * @return 计算后的颜色
     */
    public static int getColorWithRatio(int color1, int color2, float ratio)
    {
        return (int) (color2 * ratio + color1 * (1 - ratio));
    }
}
