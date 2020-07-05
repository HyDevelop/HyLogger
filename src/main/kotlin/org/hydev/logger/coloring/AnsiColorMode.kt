package org.hydev.logger.coloring

import org.hydev.logger.toAnsi256
import org.hydev.logger.toAnsi8
import java.awt.Color

enum class AnsiColorMode(val format: (Color) -> String)
{
    COLOR_RGB({ it.toAnsi256() }),
    COLOR_8BIT({ it.toAnsi8() });

    /**
     * Format a color into this color format with r, g, and b params.
     */
    fun format(r: Int, g: Int, b: Int) = format(Color(r, g, b))
}
