package org.hydev.logger.coloring

import org.hydev.logger.toAnsi256
import org.hydev.logger.toAnsi8
import java.awt.Color

enum class AnsiColorMode(val format: (Color) -> String)
{
    COLOR_RGB({ it.toAnsi256() }),
    COLOR_8BIT({ it.toAnsi8() });
}
