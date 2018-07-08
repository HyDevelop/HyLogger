package cc.moecraft.logger.coloring;

import lombok.Data;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 此类由 Hykilpikonna 在 18-7-7 创建!
 * Created by Hykilpikonna on 18-7-7!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class MultiPointLinearGradient
{
    private final ArrayList<GradientPoint> colors;
    private final int total;

    public MultiPointLinearGradient(GradientPoint color1, GradientPoint color2, ArrayList<GradientPoint> colors)
    {
        this.colors = colors;

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
