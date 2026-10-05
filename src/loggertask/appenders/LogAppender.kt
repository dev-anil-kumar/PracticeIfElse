package loggertask.appenders

import loggertask.model.LogMessage

abstract class LogAppender {
    abstract fun append(message: LogMessage)

}
