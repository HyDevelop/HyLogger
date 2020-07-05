package org.hydev

/**
 * Get the string with formats and colors removed.
 */
fun String.withoutFormat() = this.replace("\u001B\\[[;\\d]*m".toRegex(), "")
