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
}
