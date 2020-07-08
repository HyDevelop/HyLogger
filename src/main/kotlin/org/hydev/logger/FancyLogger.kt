package org.hydev.logger

import org.hydev.logger.coloring.LinearGradient
import org.hydev.logger.utils.TextColoringUtil
import java.awt.Color

class FancyLogger(private val logger: HyLogger)
{
    fun logGradient(message: String, color1: Color, color2: Color, vararg colors: Color)
    {
        logger.log(TextColoringUtil(message).getGradientText(color1, color2, *colors))
    }

    fun logGradient(message: String, gradient: LinearGradient)
    {
        logger.log(TextColoringUtil(message).getGradientText(gradient))
    }

    fun logGradient(message: String, gradient: LinearGradient, degrees: Double)
    {
        logger.log(TextColoringUtil.getGradientParagraph(message, gradient, degrees))
    }
}
