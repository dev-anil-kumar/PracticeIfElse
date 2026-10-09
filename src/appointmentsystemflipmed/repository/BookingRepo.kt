package appointmentsystemflipmed.repository

import appointmentsystemflipmed.enums.Specialization
import appointmentsystemflipmed.model.Booking
import appointmentsystemflipmed.model.Doctor
import java.sql.RowId
import java.util.LinkedList
import java.util.Queue
import java.util.UUID


class BookingRepo {
    private val bookingMap = HashMap<UUID, Booking>()
    private val waitList = HashMap<String, Queue<UUID>>()



    fun save(booking: Booking) {
        bookingMap[booking.id] = booking
    }

    fun delete(booking: Booking) {
        bookingMap.remove(booking.id)
    }

    fun getBookingById(bookingId: UUID): Booking? {
        return bookingMap[bookingId]
    }

    fun findByDoctor(id: UUID): List<Booking> {
        val res = ArrayList<Booking>()
        bookingMap.values.forEach {
            if (it.doctorId == id) {
                res.add(it)
            }
        }

        return res
    }

    fun findByPatient(id: UUID): List<Booking> {
        val res = ArrayList<Booking>()
        bookingMap.values.forEach {
            if (it.patientId == id) {
                res.add(it)
            }
        }

        return res
    }

    fun addToWaitlist(doctorSlotKey: String, patientId: UUID) {
        waitList.computeIfAbsent(doctorSlotKey) {
            LinkedList<UUID>()
        }.offer(patientId)
    }

    fun popFromWaitlist(doctorSlotKey: String): UUID? {
        return waitList[doctorSlotKey]?.poll()
    }

}
