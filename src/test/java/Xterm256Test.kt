
import org.hydev.logger.format.AnsiColor.RESET
import org.hydev.logger.format.AnsiConstants.BACKGROUND
import org.hydev.logger.format.AnsiConstants.ESC_PREFIX
import org.hydev.logger.format.AnsiConstants.SUFFIX

/**
 * Test all colors in the Xterm 256 colorspace
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-07-07 16:25
 */
fun main(args: Array<String>)
{
    // Preset colors
    printRange(0..7)
    printRange(0..15)
    println()

    // Grayscale colors
    printRange(232..243)
    printRange(244..255)
    println()

    // Actual colors
    val lines = Array(6) {""}
    for (i in 0..215 step 6)
    {
        for (j in (i + 16)..(i + 21))
            lines[i / 6 / 6] += toTest(j)
    }

    println(lines.joinToString("\n", "", ""))
}

fun printRange(range: IntRange)
{
    for (i in range) print(toTest(i))
    println()
}

fun toTest(i: Int) = ESC_PREFIX + BACKGROUND + "5;" + i + SUFFIX + i.toString().padStart(4, ' ') + " " + RESET
