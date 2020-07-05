package cc.moecraft.logger.environments

/**
 * 此类由 Hykilpikonna 在 2018/07/07 创建!
 * Created by Hykilpikonna on 2018/07/07!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
enum class ColorSupportLevel
{
    DEFAULT,  // 默认支持
    PRESET_ONLY,  // 只输出预设颜色, 移除RGB
    FORCED,  // 不传入Jansi
    DISABLED,  // 移除所有颜色
    OS_DEPENDENT // 如果是Windows, 就改为PRESET_ONLY, 如果是Linux或者OSX, 就改为DEFAULT
}
