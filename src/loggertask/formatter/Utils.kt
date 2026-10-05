package loggertask.formatter

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class Utils {
    companion object {

        fun getFormattedDate(timestamp: Long): String {
            val formatter = SimpleDateFormat("DD-MM-YYYY HH:MM:SS")

            return formatter.format(timestamp)
        }

    }
}
