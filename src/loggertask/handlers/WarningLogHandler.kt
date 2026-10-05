package loggertask.handlers

import loggertask.enums.LogLevel

class WarningLogHandler(nextHandler: LogHandler? = null) : LogHandler(nextHandler) {
    override fun canHandle(logLevel: LogLevel): Boolean {
        return logLevel == LogLevel.WARNING
    }
}
