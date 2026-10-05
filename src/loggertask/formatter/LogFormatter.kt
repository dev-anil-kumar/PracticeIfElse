package loggertask.formatter

import loggertask.enums.LogLevel
import loggertask.model.LogMessage


interface LogFormatter {
    fun format(logMessage: LogMessage): String
}
