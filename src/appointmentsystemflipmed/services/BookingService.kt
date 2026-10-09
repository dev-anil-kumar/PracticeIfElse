package appointmentsystemflipmed.services

import appointmentsystemflipmed.dto.DoctorSlots
import appointmentsystemflipmed.enums.Specialization
import appointmentsystemflipmed.model.Booking
import appointmentsystemflipmed.model.DoctorNotFoundException
import appointmentsystemflipmed.repository.BookingRepo
import appointmentsystemflipmed.repository.DoctorRepo
import appointmentsystemflipmed.repository.PatientRepo
import appointmentsystemflipmed.strategy.SlotRankingStrategy
import java.awt.print.Book
import java.util.UUID

class BookingService(
    val bookingRepo: BookingRepo,
    val patientRepo: PatientRepo,
    val doctorRepo: DoctorRepo,
    ) {

    fun search(specialization: Specialization, slotRankStrategy: SlotRankingStrategy): List<DoctorSlots> {
        val doctors = doctorRepo.findBySpecialization(specialization)
        val doctorSlots = ArrayList<DoctorSlots>()

        for (doctor in doctors) {
            doctor.availability.forEach { (slotKey, isAvailable) ->
                if (isAvailable) {
                    doctorSlots.add(DoctorSlots(doctor, slotKey))
                }
            }
        }

        return slotRankStrategy.rank(doctorSlots)
    }

    fun book(patientId: UUID, doctorId: UUID, slot: String): Booking {
        val doctor = doctorRepo.findById(doctorId) ?: throw DoctorNotFoundException("Doctor not found")

        val availability = doctor.availability

        if (availability.containsKey(slot).not()) {
            throw RuntimeException("Invalid slot")
        }

        //check if patient has already booked this slot with other dr
        bookingRepo.findByPatient(patientId).forEach {
            if (it.slot == slot) {
                throw RuntimeException("Patient already has an appointment at this slot")
            }
        }

        if (availability.get(slot)!!) {
            val booking = Booking(patientId, doctorId, slot)
            bookingRepo.save(booking)

            availability[slot] = false

            System.out.println("\n ${patientRepo.findById(patientId)!!.name} booked a slot at $slot with ${doctorRepo.findById(doctorId)!!.name}")

            return booking
        } else {
            //add to waitlist
            val key = "$doctorId-$slot"
            bookingRepo.addToWaitlist(key, patientId)

            throw RuntimeException("Slot already booked, added to waitlist")
        }
    }

    fun cancel(bookingId: UUID) {
        //cancel booking, and mark slot available, then check for waitlist and assign

        val booking = bookingRepo.getBookingById(bookingId) ?: throw RuntimeException("")

        val doctor = doctorRepo.findById(booking.doctorId)!!
        doctor.availability[booking.slot] = true
        bookingRepo.delete(booking)

        println("deleted booking id $booking")

        val slotKey = "${booking.doctorId}-${booking.slot}"
        val nextPatient = bookingRepo.popFromWaitlist(slotKey)
        if (nextPatient != null) {
            book(nextPatient,booking.doctorId, booking.slot)
        }

    }


    fun viewBookingByDoctor(id: UUID): List<Booking> {
        return bookingRepo.findByDoctor(id)
    }

    fun viewBookingByPatient(id: UUID): List<Booking> {
        return bookingRepo.findByPatient(id)
    }
}
