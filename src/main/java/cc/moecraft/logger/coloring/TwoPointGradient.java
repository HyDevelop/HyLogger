package cc.moecraft.logger.coloring;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
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
