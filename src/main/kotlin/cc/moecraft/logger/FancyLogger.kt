package cc.moecraft.logger

import cc.moecraft.logger.coloring.AnsiColorMode
import cc.moecraft.logger.coloring.MultiPointLinearGradient
import cc.moecraft.logger.format.AnsiConstants
import cc.moecraft.logger.text.Paragraph
import cc.moecraft.logger.utils.TextColoringUtil
import java.awt.Color
import java.util.*

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
class FancyLogger(private val logger: HyLogger)
{
    var colorMode = AnsiColorMode.COLOR_RGB

    @Deprecated("")
    fun logRAINBOW(message: String)
    {
        val newMessage = StringBuilder()
        val random = Random()
        for (c in message.toCharArray())
        {
            newMessage.append(AnsiConstants.colors!![random.nextInt(AnsiConstants.colors!!.size)]).append(c)
        }
        logger.log(newMessage.toString())
    }

    fun logGradient(message: String, color1: Color, color2: Color, vararg colors: Color)
    {
        logger.log(TextColoringUtil(message).getGradientText(color1, color2, *colors))
    }

    fun logGradient(message: String, gradient: MultiPointLinearGradient)
    {
        logger.log(TextColoringUtil(message).getGradientText(gradient))
    }

    fun logGradient(message: Paragraph, gradient: MultiPointLinearGradient, degrees: Int)
    {
        logger.log(TextColoringUtil.getGradientParagraph(colorMode, message.toCharArray(), gradient, degrees))
    }

    fun logGradient(message: Paragraph, gradient: MultiPointLinearGradient)
    {
        logger.log(TextColoringUtil.getGradientParagraph(colorMode, message.toCharArray(), gradient, 0))
    }
}
