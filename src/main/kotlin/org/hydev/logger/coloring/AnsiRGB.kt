package org.hydev.logger.coloring

import org.hydev.logger.HyLoggerConfig.colorMode
import org.hydev.logger.format.AnsiConstants.BACKGROUND
import org.hydev.logger.format.AnsiConstants.ESC_PREFIX
import org.hydev.logger.format.AnsiConstants.FOREGROUND
import org.hydev.logger.format.AnsiConstants.SUFFIX
import java.awt.Color

class AnsiRGB(var foreground: Color, var background: Color? = null)
{
    /**
     * Get ANSI color code.
     *
     * @return ANSI color code.
     */
    override fun toString(): String
    {
        var result = ESC_PREFIX + FOREGROUND + colorMode.format(foreground)
        if (background != null) result += BACKGROUND + colorMode.format(background!!)
        return result + SUFFIX
    }
}
