package cc.moecraft.logger.text

import java.util.*

class Paragraph(var sentences: ArrayList<String>)
{
    constructor(vararg sentences: String) : this(ArrayList<String>())
    {
        addSentences(*sentences)
    }

    fun addSentences(vararg sentences: String): Paragraph
    {
        return addSentences(listOf(*sentences))
    }

    fun addSentences(sentences: Collection<String>): Paragraph
    {
        this.sentences.addAll(sentences)
        return this
    }

    fun processNewLines()
    {
        sentences =
            ArrayList(listOf(*toString().split("\n").toTypedArray()))
    }

    fun countLongestLineChars(): Int
    {
        var max = 0
        for (sentence in sentences)
        {
            if (sentence.length > max) max = sentence.length
        }
        return max
    }

    fun toCharArray(): Array<CharArray>
    {
        processNewLines()
        val charArray =
            Array(sentences.size) { CharArray(countLongestLineChars()) }
        for (i in sentences.indices)
        {
            val sentence = sentences[i].toCharArray()
            System.arraycopy(sentence, 0, charArray[i], 0, sentence.size)
        }
        return charArray
    }

    override fun toString(): String
    {
        val result = StringBuilder()
        result.append(sentences[0])
        for (i in 1 until sentences.size)
        {
            result.append("\n").append(sentences[i])
        }
        return result.toString()
    }

}
