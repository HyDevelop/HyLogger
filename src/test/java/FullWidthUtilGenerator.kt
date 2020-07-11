import org.hydev.logger.line

/**
 * This script is used to generate full width character detector from
 * EastAsianWidth.txt (http://www.unicode.org/Public/UCD/latest/ucd/EastAsianWidth.txt)
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-07-10 20:32
 */
fun main(args: Array<String>)
{
    val fileContent = {}.javaClass.getResource("/EastAsianWidth.txt").readText()
    val values = ArrayList<String>()
    val ranges = ArrayList<String>()

    for (rawLine in fileContent.lines())
    {
        // Ignore comments
        val line = rawLine.substringBefore('#')
        if (line.isBlank()) continue

        val split = line.split(";")

        // Ignore UTF Scalar for now
        if (split[0].split("..")[0].length >= 5) continue

        // A = Ambiguous (Treated as half-width)
        // N, Na, H = Half-width
        // F, W = Full-width
        val code = split[1][0].toString()
        if (!(code == "F" || code == "W")) continue

        // Get range
        val value = "'\\u${split[0]}'"
        val isRange = value.contains("..");
        if (!isRange) values.add(value)
        if (isRange)
        {
            val rangeSplit = value.split("..")
            ranges.add("in ${rangeSplit[0]}'..'\\u${rangeSplit[1]}")
        }
    }

    // Create string
    val result = StringBuilder()
    var line = "    "

    result.line("when (char)").line("{").append("")

    values.forEach {
        if (line.length + it.length > 110)
        {
            result.line(line)
            line = "    "
        }

        line += "$it,"
    }

    result.line(line)
    line = "    "

    ranges.forEach {
        if (line.length + it.length > 110)
        {
            result.line(line)
            line = "    "
        }

        line += "$it,"
    }

    result.line(line.trimEnd(',') + " -> true").line("    else -> false").line("}")

    print(result)
}
