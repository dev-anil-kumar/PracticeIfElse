package loggertask.appenders

import loggertask.formatter.JsonTextFormatter
import loggertask.formatter.LogFormatter
import loggertask.model.LogMessage
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException

class FileLogAppender(private val formatter: LogFormatter, filename: String = "logs.txt") : LogAppender() {

    private lateinit var bufferWriter: BufferedWriter

    init {
        try {
            bufferWriter = BufferedWriter(FileWriter(filename))
        } catch (e: IOException) {
            throw RuntimeException("Failed to open file")
        }
    }

    override fun append(message: LogMessage) {
        try {
            bufferWriter.append(formatter.format(message))
            bufferWriter.newLine()
            bufferWriter.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
