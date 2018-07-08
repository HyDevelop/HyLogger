package cc.moecraft.logger.environments;

/**
 * 此类由 Hykilpikonna 在 2018/07/07 创建!
 * Created by Hykilpikonna on 2018/07/07!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public enum ColorSupportLevel
{
    DEFAULT,     // 默认支持
    PRESET_ONLY, // 只输出预设颜色, 移除RGB
    PASSTHROUGH, // Jansi的Passthrough
    FORCED       // 不传入Jansi
}
