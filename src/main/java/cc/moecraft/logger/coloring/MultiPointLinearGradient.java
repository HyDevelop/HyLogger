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

    public MultiPointLinearGradient(Color color1, GradientPoint color2, GradientPoint ... colors)
    {
        this(new GradientPoint(color1), color2, new ArrayList<>(Arrays.asList(colors)));
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

    /**
     * 以文字数量缩放Map
     *
     * 例子: amount = 7
     *   0, 红
     *   100, 蓝
     *   200, 橙
     *   250, 紫
     *
     * 缩放后:
     *   0, 红
     *   2, 蓝 (2.8)
     *   5, 橙 (5.6)
     *   7, 紫
     *
     * @param mappedSizes 映射过的Map
     * @param amount 文字数量
     * @return 缩放后的Map
     */
    public static List<Map.Entry<Integer, GradientPoint>> scaleSizes(List<Map.Entry<Integer, GradientPoint>> mappedSizes, int amount)
    {
        List<Map.Entry<Integer, GradientPoint>> result = new ArrayList<>();

        double scale = (double) amount / (double) mappedSizes.get(mappedSizes.size() - 1).getKey();

        for (Map.Entry<Integer, GradientPoint> mappedSize : mappedSizes)
        {
            result.add(new HashMap.SimpleEntry<>(
                    (int) (mappedSize.getKey() * scale),
                    mappedSize.getValue()
            ));
        }

        return result;
    }

    /**
     * 获取最近的两个颜色
     *
     * 例子: i=2
     *   0, 红
     *   2, 蓝
     *   5, 橙
     *   7, 紫
     *
     * 获取后:
     *   蓝
     *   橙
     *
     * @param scaledMap 比例Map
     * @param index i
     * @return 最近的两个颜色
     */
    public static List<Map.Entry<Integer, GradientPoint>> getNearestTwoColors(List<Map.Entry<Integer, GradientPoint>> scaledMap, int index)
    {
        List<Map.Entry<Integer, GradientPoint>> result = new ArrayList<>();

        result.add(scaledMap.get(0));
        result.add(scaledMap.get(scaledMap.size() - 1));

        for (int i = 0; i < scaledMap.size(); i++)
        {
            int currentAmount = scaledMap.get(i).getKey();

            if (currentAmount <= index && currentAmount > result.get(0).getKey())
            {
                result.remove(0);
                result.add(0, scaledMap.get(i));
            }

            if (currentAmount >= index && currentAmount < result.get(1).getKey())
            {
                result.remove(1);
                result.add(scaledMap.get(i));
                break;
            }
        }

        return result;
    }

    /**
     * 按数量分割为非自然渐变, 获取一个代表所有渐变色的数组
     * @param amount 数量
     * @return 渐变
     */
    public AnsiRGB[] getColors(int amount)
    {
        AnsiRGB[] colors = new AnsiRGB[amount];
        List<Map.Entry<Integer, MultiPointLinearGradient.GradientPoint>> scaledSizes = MultiPointLinearGradient.scaleSizes(mappedSizes, amount);

        for (int i = 0; i < amount; i++)
        {
            List<Map.Entry<Integer, MultiPointLinearGradient.GradientPoint>> nearestTwo = MultiPointLinearGradient.getNearestTwoColors(scaledSizes, i);

            Color color1 = nearestTwo.get(0).getValue().getColor();
            Color color2 = nearestTwo.get(1).getValue().getColor();

            if (color1.equals(color2))
            {
                colors[i] = new AnsiRGB(color1);
                continue;
            }

            int value1 = nearestTwo.get(0).getKey();
            int value2 = nearestTwo.get(1).getKey();

            float ratio = (float) (i - value1) / (float) (value2 - value1);

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

    @Data
    public static class GradientPoint
    {
        private Color color;
        private int amount;

        public GradientPoint(Color color, int amount)
        {
            if (amount <= 0) throw new RuntimeException("颜色Amount不能小于0");
            this.color = color;
            this.amount = amount;
        }

        public GradientPoint(Color color)
        {
            this(color, 100);
        }
    }
}
