package org.hydev.logger.appenders

import org.hydev.logger.HyLoggerConfig
import org.hydev.logger.withoutFormat
import org.hydev.logger.withoutRGB

enum class ColorCompatibility(val log: (String) -> Unit)
{
    // Only output preset colors and remove xterm-256 colors
    PRESET_ONLY({ HyLoggerConfig.out.println(it.withoutRGB()) }),

    // Always output color
    FORCED({ HyLoggerConfig.out.println(it) }),

    // Remove all colors
    DISABLED({ HyLoggerConfig.out.println(it.withoutFormat()) }),
}
