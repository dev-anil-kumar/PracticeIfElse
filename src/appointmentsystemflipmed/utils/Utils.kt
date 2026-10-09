package appointmentsystemflipmed.utils

import java.time.LocalTime

class Utils {
    companion object {
        fun convertStringToLocalTime(slot: String): LocalTime {
            val start = slot.substringBefore("-").trim()
            return LocalTime.parse(start)
        }
    }

}
