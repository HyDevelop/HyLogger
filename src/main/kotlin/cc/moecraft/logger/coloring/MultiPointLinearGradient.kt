package cc.moecraft.logger.coloring

import java.awt.Color
import java.util.AbstractMap.SimpleEntry

class MultiPointLinearGradient(color1: GradientPoint, color2: GradientPoint, colors: MutableList<GradientPoint>)
{
    private val colors: MutableList<GradientPoint>
    private val mappedSizes: MutableList<Map.Entry<Int, GradientPoint>>
    private val total: Int

    constructor(color1: Color, color2: GradientPoint, vararg colors: GradientPoint) :
        this(GradientPoint(color1), color2, mutableListOf(*colors))

    constructor(color1: Color, color2: Color, vararg colors: Color) :
        this(GradientPoint(color1), GradientPoint(color2), ArrayList<GradientPoint>(convert(listOf(*colors))))

    init
    {
        colors.add(0, color1)
        colors.add(1, color2)
        this.colors = colors
        mappedSizes = mapSizes(colors)
        total = mappedSizes[mappedSizes.size - 1].key
    }

    /**
     * 按数量分割为非自然渐变, 获取一个代表所有渐变色的数组
     * @param amount 数量
     * @return 渐变
     */
    fun getColors(amount: Int): List<AnsiRGB>
    {
        val colors = ArrayList<AnsiRGB>()
        val scaledSizes = scaleSizes(mappedSizes, amount)
        for (i in 0 until amount)
        {
            val nearestTwo = getNearestTwoColors(scaledSizes, i)

            val color1 = nearestTwo[0].value.color
            val color2 = nearestTwo[1].value.color

            if (color1 == color2)
            {
                colors[i] = AnsiRGB(color1)
                continue
            }

            val value1 = nearestTwo[0].key
            val value2 = nearestTwo[1].key
            val ratio = (i - value1).toFloat() / (value2 - value1).toFloat()
            val resultR = getColorWithRatio(color1.red, color2.red, ratio)
            val resultG = getColorWithRatio(color1.green, color2.green, ratio)
            val resultB = getColorWithRatio(color1.blue, color2.blue, ratio)
            val result = AnsiRGB(Color(resultR, resultG, resultB))
            colors[i] = result
        }
        return colors
    }

    class GradientPoint constructor(var color: Color, var amount: Int = 100)

    companion object
    {
        private fun convert(colors: List<Color>): List<GradientPoint>
        {
            val result: MutableList<GradientPoint> = ArrayList()
            for (color in colors) result.add(GradientPoint(color))
            return result
        }

        /**
         * 把一个相对数值的列表转换为绝对数值的列表
         *
         * 例子:
         * 100, 红
         * 100, 蓝
         * 100, 橙
         * 50, 紫
         *
         * 转换后:
         * 0, 红
         * 100, 蓝
         * 200, 橙
         * 250, 紫
         *
         * @param gradientPoints 相对数值的列表
         * @return 绝对数值的列表
         */
        fun mapSizes(gradientPoints: List<GradientPoint>): ArrayList<Map.Entry<Int, GradientPoint>>
        {
            val result = ArrayList<Map.Entry<Int, GradientPoint>>()
            var total = 0 - gradientPoints[0].amount
            for (gradientPoint in gradientPoints)
            {
                total += gradientPoint.amount
                result.add(SimpleEntry(total, gradientPoint))
            }
            return result
        }

        /**
         * 以文字数量缩放Map
         *
         * 例子: amount = 7
         * 0, 红
         * 100, 蓝
         * 200, 橙
         * 250, 紫
         *
         * 缩放后:
         * 0, 红
         * 2, 蓝 (2.8)
         * 5, 橙 (5.6)
         * 7, 紫
         *
         * @param mappedSizes 映射过的Map
         * @param amount 文字数量
         * @return 缩放后的Map
         */
        fun scaleSizes(mappedSizes: List<Map.Entry<Int, GradientPoint>>, amount: Int): List<Map.Entry<Int, GradientPoint>>
        {
            val result: MutableList<Map.Entry<Int, GradientPoint>> = ArrayList()
            val scale = amount.toDouble() / mappedSizes[mappedSizes.size - 1].key.toDouble()
            for ((key, value) in mappedSizes)
            {
                result.add(SimpleEntry((key * scale).toInt(), value))
            }
            return result
        }

        /**
         * 获取最近的两个颜色
         *
         * 例子: i=2
         * 0, 红
         * 2, 蓝
         * 5, 橙
         * 7, 紫
         *
         * 获取后:
         * 蓝
         * 橙
         *
         * @param scaledMap 比例Map
         * @param index i
         * @return 最近的两个颜色
         */
        fun getNearestTwoColors(scaledMap: List<Map.Entry<Int, GradientPoint>>, index: Int): List<Map.Entry<Int, GradientPoint>>
        {
            val result: MutableList<Map.Entry<Int, GradientPoint>> = ArrayList()
            result.add(scaledMap[0])
            result.add(scaledMap[scaledMap.size - 1])
            for (i in scaledMap.indices)
            {
                val currentAmount = scaledMap[i].key
                if (currentAmount <= index && currentAmount > result[0].key)
                {
                    result.removeAt(0)
                    result.add(0, scaledMap[i])
                }
                if (currentAmount >= index && currentAmount < result[1].key)
                {
                    result.removeAt(1)
                    result.add(scaledMap[i])
                    break
                }
            }
            return result
        }

        /**
         * 按比例计算颜色
         * @param color1 颜色值1
         * @param color2 颜色值2
         * @param ratio 比例
         * @return 计算后的颜色
         */
        fun getColorWithRatio(color1: Int, color2: Int, ratio: Float): Int
        {
            return (color2 * ratio + color1 * (1 - ratio)).toInt()
        }
    }
}
