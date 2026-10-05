package loggertask

import loggertask.enums.LogLevel
import loggertask.handlers.LogHandler
import loggertask.model.LogMessage

class Logger private constructor() {
    private var handlerChain: LogHandler = LogHandlerConfiguration.build()

    companion object {
        private val instance = Logger()

        fun getInstance() = instance
    }


    fun logInfo(logMessage: String) {
        log(LogLevel.INFO, logMessage)
    }

    fun logWarning(logMessage: String) {
        log(LogLevel.WARNING, logMessage)
    }

    fun logError(logMessage: String) {
        log(LogLevel.ERROR, logMessage)
    }

    fun logFatalError(logMessage: String) {
        log(LogLevel.FATAL, logMessage)
    }


    fun log(logLevel: LogLevel, message: String) {
        val logMessage = LogMessage(System.currentTimeMillis(), message, logLevel)
        handlerChain.handleMessage(logMessage)
    }


}
