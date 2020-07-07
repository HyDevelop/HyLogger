package org.hydev.logger.coloring

import org.hydev.logger.b
import org.hydev.logger.g
import org.hydev.logger.r
import java.awt.Color

enum class AnsiColorMode(val format: (Color) -> String)
{
    TRUE_COLOR_24BIT({ "2;${it.r};${it.g};${it.b}" }),
    XTERM_256_8BIT({ "5;${it.to8Bit()}" });
}
