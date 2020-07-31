
import org.hydev.logger.HyLoggerConfig
import java.lang.System.err

/**
 * TODO: Write a description for this class!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-07-31 16:59
 */
fun main(args: Array<String>)
{
    println("安装到 Sysout 之前w")

    // 安装到 Sysout
    HyLoggerConfig.installSysOut()

    println("安装到 Sysout 之后w")
    Thread.sleep(50)

    err.println("丢的异常也可以哦w")
    val i = 1/0
}
