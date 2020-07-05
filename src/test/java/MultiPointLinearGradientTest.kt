import cc.moecraft.logger.coloring.MultiPointLinearGradient
import cc.moecraft.logger.coloring.MultiPointLinearGradient.GradientPoint
import java.awt.Color
import java.util.*

/**
 * 此类由 Hykilpikonna 在 18-7-7 创建!
 * Created by Hykilpikonna on 18-7-7!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 
 */
internal object MultiPointLinearGradientTest
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        val list: MutableList<GradientPoint> = ArrayList()
        list.add(GradientPoint(Color.RED))
        list.add(GradientPoint(Color.BLUE))
        list.add(GradientPoint(Color.ORANGE))
        list.add(GradientPoint(Color.PINK, 50))
        val mappedSizes: List<Map.Entry<Int, GradientPoint>> =
            MultiPointLinearGradient.mapSizes(list)
        println(mappedSizes)
        val scaledSizes =
            MultiPointLinearGradient.scaleSizes(mappedSizes, 7)
        println(scaledSizes)
        val nearestTwo =
            MultiPointLinearGradient.getNearestTwoColors(scaledSizes, 3)
        println(nearestTwo)
    }
}
