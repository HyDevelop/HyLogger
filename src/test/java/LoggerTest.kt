
import org.hydev.logger.HyLogger
import org.hydev.logger.coloring.GradientPresets.BOP
import org.hydev.logger.coloring.GradientPresets.BPR
import org.hydev.logger.coloring.GradientPresets.RAINBOW
import java.awt.Color

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
object LoggerTest
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        val logger = HyLogger("LoggerTest")
        logger.timing.reset()
        logger.timing.time().reset()
        logger.log("一条测试Log消息")
        logger.timing.time().reset()
        logger.debug("一条测试Debug消息")
        logger.timing.time().reset()
        logger.error("一条测试Error消息")
        logger.timing.time().reset()
        logger.warning("一条测试Warning消息")
        logger.timing.time().reset()
        logger.fancy.logGradient("测试渐变从深蓝到浅蓝", Color.BLUE, Color.CYAN)
        logger.timing.time().reset()
        logger.fancy.logGradient("测试渐变从橘色到浅蓝", Color.ORANGE, Color.CYAN)
        logger.timing.time().reset()
        logger.fancy.logGradient(
            "测试黄绿渐变到天蓝",
            Color(0, 242, 96),
            Color(80, 161, 230)
        )
        logger.timing.time().reset()
        logger.fancy.logGradient(
            "测试橙色渐变到粉色",
            Color(255, 140, 0),
            Color(255, 0, 128)
        )
        logger.timing.time().reset()
        logger.fancy.logGradient("##############测试彩虹多点渐变预设##############", RAINBOW)
        logger.timing.time().reset()
        logger.fancy.logGradient("##############测试蓝到紫到红多点渐变##############", BPR)
        logger.timing.time().reset()
        run {
            logger.log("测试Paragraph斜向线性渐变 #1:")
            val paragraph = """
                ┬ ┬┬ ┬┬  ┌─┐┌─┐┌─┐┌─┐┬─┐
                ├─┤└┬┘│  │ ││ ┬│ ┬├┤ ├┬┘
                ┴ ┴ ┴ ┴─┘└─┘└─┘└─┘└─┘┴└─""".trimIndent()
            logger.fancy.logGradient(paragraph, BPR, 15)
        }
        logger.timing.time().reset()
        run {
            logger.log("测试Paragraph斜向线性渐变 #2:")
            val paragraph =
                                """    __  __      __                               
                   / / / /_  __/ /   ____  ____ _____ ____  _____
                  / /_/ / / / / /   / __ \/ __ `/ __ `/ _ \/ ___/
                 / __  / /_/ / /___/ /_/ / /_/ / /_/ /  __/ /    
                /_/ /_/\__, /_____/\____/\__, /\__, /\___/_/
                      /____/            /____//____/             """.trimIndent()
            logger.fancy.logGradient(paragraph, BOP, 60)
        }
        logger.timing.time().reset()
    }
}
