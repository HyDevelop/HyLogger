package org.hydev.logger.appenders

import org.fusesource.jansi.AnsiConsole
import org.hydev.logger.withoutFormat
import org.hydev.logger.withoutRGB

enum class ColorCompatibility(val log: (String) -> Unit)
{
    // Default support (let Jansi decide)
    DEFAULT({ AnsiConsole.out.println(it) }),

    // Only output preset colors and remove xterm-256 colors
    PRESET_ONLY({ AnsiConsole.out.println(it.withoutRGB()) }),

    // Always output color
    FORCED({ println(it) }),

    // Remove all colors
    DISABLED({ println(it.withoutFormat()) }),
}
