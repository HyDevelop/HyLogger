
import org.hydev.logger.HyLoggerConfig
import org.junit.jupiter.api.MethodOrderer.Alphanumeric
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.TestMethodOrder

/**
 * Unit tests for
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-07-31 16:40
 */
@TestInstance(PER_CLASS) @TestMethodOrder(Alphanumeric::class)
class HyLoggerUnitTest
{
    @Test
    fun a1000_SysOut(){
        fun testOnce()
        {
            print("Hello ")
            println("World")
            println(null)
            Thread.sleep(50)
            System.err.println("Error");
            Thread.sleep(50)
        }

        testOnce()
        HyLoggerConfig.installSysOut()
        testOnce()
    }
}
