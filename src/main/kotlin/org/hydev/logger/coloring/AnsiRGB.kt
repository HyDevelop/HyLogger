package org.hydev.logger.coloring

import org.hydev.logger.HyLoggerConfig.colorMode
import org.hydev.logger.format.AnsiConstants
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
        var result = AnsiConstants.ESC_PREFIX + AnsiConstants.FOREGROUND + colorMode.format(foreground)
        if (background != null) result += AnsiConstants.BACKGROUND + colorMode.format(background!!)
        return result + AnsiConstants.SUFFIX
    }
}
