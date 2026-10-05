package loggertask.appenders

import loggertask.formatter.LogFormatter
import loggertask.formatter.PlainTextFormatter
import loggertask.formatter.Utils
import loggertask.model.LogMessage
import kotlin.math.log

class ConsoleLogAppender(private val formatter: LogFormatter) : LogAppender() {

    override fun append(message: LogMessage) {
        println(formatter.format(message))
    }
}
