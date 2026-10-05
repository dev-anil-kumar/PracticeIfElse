package loggertask

import loggertask.appenders.LogAppender
import loggertask.enums.LogLevel
import loggertask.handlers.*
import loggertask.model.LogMessage

class LogHandlerConfiguration {

    companion object {
        private val debugLogHandler = DebugLogHandler()
        private val infoLogHandler = InfoLogHandler()
        private val warningLogHandler = WarningLogHandler()
        private val errorLogHandler = ErrorLogHandler()
        private val fatalLogHandler = FatalLogHandler()


        fun build(): LogHandler {
            debugLogHandler.next = infoLogHandler
            infoLogHandler.next = warningLogHandler
            warningLogHandler.next = errorLogHandler
            errorLogHandler.next = fatalLogHandler

            return debugLogHandler
        }

        fun addAppenderForLevel(logLevel: LogLevel, logAppender: LogAppender) {
            when(logLevel) {

                LogLevel.DEBUG -> debugLogHandler.subscribe(logAppender)
                LogLevel.INFO -> infoLogHandler.subscribe(logAppender)
                LogLevel.WARNING -> warningLogHandler.subscribe(logAppender)
                LogLevel.ERROR -> errorLogHandler.subscribe(logAppender)
                LogLevel.FATAL -> fatalLogHandler.subscribe(logAppender)
                LogLevel.TRACE -> {}
            }
        }
    }


}
