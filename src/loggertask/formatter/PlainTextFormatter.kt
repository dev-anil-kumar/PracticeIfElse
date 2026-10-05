package loggertask.formatter

import loggertask.model.LogMessage

class PlainTextFormatter : LogFormatter {
    override fun format(logMessage: LogMessage): String {
       return "${Utils.getFormattedDate(logMessage.timestamp)} ${logMessage.logLevel} ${logMessage.message}"
    }
}
