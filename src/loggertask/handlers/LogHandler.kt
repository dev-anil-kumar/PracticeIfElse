package loggertask.handlers

import loggertask.appenders.LogAppender
import loggertask.enums.LogLevel
import loggertask.model.LogMessage
import java.util.concurrent.CopyOnWriteArrayList

abstract class LogHandler(var next: LogHandler? = null) {

    private val appenders = CopyOnWriteArrayList<LogAppender>()

    abstract fun canHandle(logLevel: LogLevel): Boolean

    fun subscribe(observer: LogAppender){
        appenders.add(observer)
    }

    private fun notifyObservers(message: LogMessage) {
        appenders.forEach {
            it.append(message)
        }
    }

    fun handleMessage(logMessage: LogMessage) {
        if (canHandle(logMessage.logLevel)) {
            notifyObservers(logMessage)
        } else next?.handleMessage(logMessage)
    }
}
