package loggertask

import loggertask.appenders.ConsoleLogAppender
import loggertask.appenders.FileLogAppender
import loggertask.enums.LogLevel
import loggertask.formatter.PlainTextFormatter
import kotlin.math.log


fun main() {
    println("Hello World!")

    val logger = Logger.getInstance();

    LogHandlerConfiguration.addAppenderForLevel(
        LogLevel.INFO, ConsoleLogAppender(PlainTextFormatter())
    )

    LogHandlerConfiguration.addAppenderForLevel(
        LogLevel.ERROR, ConsoleLogAppender(PlainTextFormatter())
    )

    LogHandlerConfiguration.addAppenderForLevel(
        LogLevel.ERROR, FileLogAppender(PlainTextFormatter(), "logs.txt")
    )


//    logger.logInfo("info message")
    logger.logError("error message, got next error")


}
