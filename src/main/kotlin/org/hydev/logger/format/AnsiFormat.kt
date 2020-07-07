package org.hydev.logger.format

import org.hydev.logger.format.AnsiConstants.ESC_PREFIX
import org.hydev.logger.format.AnsiConstants.SUFFIX

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
enum class AnsiFormat(var code: Int, vararg val placeholders: Char)
{
    RESET(0),
    HIGH_INTENSITY(1, 'l'),
    LOW_INTENSITY(2),
    ITALIC(3, 'o'),
    UNDERLINE(4, 'n'),
    BLINK(5),
    RAPID_BLINK(6),
    REVERSE_VIDEO(7),
    INVISIBLE_TEXT(8);

    override fun toString() = value
    val value = ESC_PREFIX + code + SUFFIX
}
