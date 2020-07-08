package org.hydev.logger

import org.hydev.logger.coloring.LinearGradient
import org.hydev.logger.utils.TextColoringUtil
import java.awt.Color

class FancyLogger(private val logger: HyLogger)
{
    fun gradient(message: String, c1: Color, c2: Color, vararg colors: Color)
        = gradient(message, LinearGradient(c1, c2, *colors))

    fun gradient(message: String, gradient: LinearGradient)
        = logger.log(gradient.colorText(message))

    fun gradient(message: String, gradient: LinearGradient, degrees: Double)
    {
        logger.log(TextColoringUtil.getGradientParagraph(message, gradient, degrees))
    }
}
