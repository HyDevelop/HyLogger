package color

import org.hydev.logger.HyLogger
import org.hydev.logger.HyLoggerConfig
import org.hydev.logger.background
import org.hydev.logger.coloring.AnsiColorMode.TRUE_COLOR_24BIT
import org.hydev.logger.coloring.AnsiColorMode.XTERM_256_8BIT
import org.hydev.logger.foreground
import org.hydev.logger.format.AnsiColor
import org.hydev.logger.format.AnsiColor.RESET
import java.awt.Color

/**
 * This class prints a detailed analysis of the color capability of the terminal client.
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-07-07 14:00
 */
fun main()
{
    for (test in tests)
    {
        println()
        HyLoggerConfig.colorMode = TRUE_COLOR_24BIT
        println("${RESET}True Color ${test.key}:")
        test.value()
        println()
        HyLoggerConfig.colorMode = XTERM_256_8BIT
        println("${RESET}Xterm 256 ${test.key}:")
        test.value()
    }
}

// All the tests
var tests = linkedMapOf(
    "Background Foreground Test" to {
        HyLogger("Test").log(AnsiColor.RED.background + AnsiColor.GREEN + "Hello world!")
    },

    "Grayscale Test" to {
        for (r in 0..255 step 16)
        {
            print(Color(r, r, r).foreground() + "█")
        }
        println()
    },

    "Blue Test" to {
        for (b in 0..255)
        {
            if (b % 16 == 0) println()

            print("${Color.LIGHT_GRAY.foreground()}${Color(0, 0, b).background()}" +
                "${b.toString().padStart(4, ' ')} $RESET ")
        }
    },

    "All Colors Test" to {
        for (r in 0..255 step 16)
        {
            for (g in 0..255 step 16)
            {
                for (b in 0..255 step 16)
                {
                    print(Color(r, g, b).foreground() + "█")
                }
                print(" ")
            }
            println()
        }
    }
)
