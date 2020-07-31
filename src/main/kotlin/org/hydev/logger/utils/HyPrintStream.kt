package org.hydev.logger.utils

import org.hydev.logger.HyLogger
import java.io.OutputStream
import java.io.PrintStream

/**
 * PrintStream Wrapper
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-07-13 22:05
 */
class HyPrintStream(val original: PrintStream, val log: (Any) -> Unit = { HyLogger.general.log(it) })
    : PrintStream(object: OutputStream() { override fun write(b: Int) {}})
{
    override fun write(b: ByteArray) = original.write(b)
    override fun write(b: Int) = original.write(b)
    override fun write(buf: ByteArray, off: Int, len: Int) = original.write(buf, off, len)
    override fun checkError() = super.checkError() || original.checkError()
    override fun close() = run { super.close(); original.close() }
    override fun flush() = run { super.flush(); original.flush() }
    private fun write(s: String?) = original.print(s)
    private fun write(buf: CharArray) = original.print(buf)

    /* Methods that do not terminate lines */

    override fun print(b: Boolean) = original.print(b)
    override fun print(c: Char) = original.print(c)
    override fun print(i: Int) = original.print(i)
    override fun print(l: Long) = original.print(l)
    override fun print(f: Float) = original.print(f)
    override fun print(d: Double) = original.print(d)
    override fun print(s: CharArray) = original.print(s)
    override fun print(s: String?) = original.print(s)
    override fun print(obj: Any) = original.print(obj)

    /* Methods that do terminate lines */

    override fun println() = log("")
    override fun println(x: Boolean) = log(x)
    override fun println(x: Char) = log(x)
    override fun println(x: Int) = log(x)
    override fun println(x: Long) = log(x)
    override fun println(x: Float) = log(x)
    override fun println(x: Double) = log(x)
    override fun println(x: CharArray) = log(x)
    override fun println(x: String?) = log(x ?: "null")
    override fun println(x: Any) = log(x)
}
