package loggertask.model

import loggertask.enums.LogLevel


data class LogMessage(
    val timestamp: Long,
    val message: String,
    val logLevel: LogLevel
)
