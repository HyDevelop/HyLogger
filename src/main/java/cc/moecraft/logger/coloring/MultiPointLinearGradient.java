package cc.moecraft.logger.coloring;

import lombok.Data;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 此类由 Hykilpikonna 在 18-7-7 创建!
 * Created by Hykilpikonna on 18-7-7!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 
 */
public class MultiPointLinearGradient
{
    private final ArrayList<GradientPoint> colors;
    private final ArrayList<Map.Entry<Integer, GradientPoint>> mappedSizes;
    private final int total;

    public MultiPointLinearGradient(GradientPoint color1, GradientPoint color2, ArrayList<GradientPoint> colors)
    {
        colors.add(0, color1);
        colors.add(1, color2);

        this.colors = colors;
        this.mappedSizes = mapSizes(colors);
        this.total = mappedSizes.get(mappedSizes.size() - 1).getKey();
    }


    public MultiPointLinearGradient(Color color1, Color color2, Color ... colors)
    {
        this(new GradientPoint(color1), new GradientPoint(color2), new ArrayList<>(convert(colors)));
    }

    private static List<GradientPoint> convert(Color[] colors)
    {
        List<GradientPoint> result = new ArrayList<>();
        for (Color color : colors) result.add(new GradientPoint(color));
        return result;
    }

    /**
     * 把一个相对数值的列表转换为绝对数值的列表
     *
     * 例子:
     *   100, 红
     *   100, 蓝
     *   100, 橙
     *   50, 紫
     *
     * 转换后:
     *   0, 红
     *   100, 蓝
     *   200, 橙
     *   250, 紫
     *
     * @param gradientPoints 相对数值的列表
     * @return 绝对数值的列表
     */
    public static ArrayList<Map.Entry<Integer, GradientPoint>> mapSizes(List<GradientPoint> gradientPoints)
    {
        ArrayList<Map.Entry<Integer, GradientPoint>> result = new ArrayList<>();

        int total = 0 - gradientPoints.get(0).amount;

        for (GradientPoint gradientPoint : gradientPoints)
        {
            total += gradientPoint.amount;
            result.add(new HashMap.SimpleEntry<>(total, gradientPoint));
        }

        return result;
    }
    }
}
