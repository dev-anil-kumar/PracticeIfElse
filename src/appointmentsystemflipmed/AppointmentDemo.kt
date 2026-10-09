package appointmentsystemflipmed

import appointmentsystemflipmed.enums.Specialization
import appointmentsystemflipmed.model.Doctor
import appointmentsystemflipmed.model.Patient
import appointmentsystemflipmed.repository.BookingRepo
import appointmentsystemflipmed.repository.DoctorRepo
import appointmentsystemflipmed.repository.PatientRepo
import appointmentsystemflipmed.services.BookingService
import appointmentsystemflipmed.services.DoctorService
import appointmentsystemflipmed.services.PatientService
import appointmentsystemflipmed.strategy.RatingBasedRankStrategy
import loggertask.appenders.ConsoleLogAppender
import loggertask.appenders.FileLogAppender
import loggertask.enums.LogLevel
import loggertask.formatter.PlainTextFormatter
import kotlin.math.log


fun main() {
    println("Hello World!")

    val doctorRepo = DoctorRepo()
    val bookingRepo = BookingRepo()
    val patientRepo = PatientRepo()

    val doctorService = DoctorService(doctorRepo)
    val patientService = PatientService(patientRepo)
    val bookingService = BookingService(bookingRepo, patientRepo, doctorRepo)
    val rankStrategy = RatingBasedRankStrategy()

    val doctor1 = doctorService.register("doctor1", Specialization.CARDIOLOGIST, 4.5)
    val doctor2 = doctorService.register("doctor2", Specialization.GENERAL, 3.5)
    val doctor3 = doctorService.register("doctor3", Specialization.CARDIOLOGIST, 2.5)

    doctorService.markDoctorAvailability(doctor1.id, listOf("9:30", "10:30"))
    doctorService.markDoctorAvailability(doctor2.id, listOf("9:30", "10:30"))
    doctorService.markDoctorAvailability(doctor3.id, listOf("9:30", "10:30"))

    val p1 = patientService.register("patient1")
    val p2 = patientService.register("patient2")

    //search slots
    val doctorSlots = bookingService.search(Specialization.CARDIOLOGIST, rankStrategy)
    doctorSlots.forEach { println("${it.doctor.name} - ${it.slot}") }

    //book slot
    val b1 = bookingService.book(p1.id, doctor1.id, "10:30")

    //bookings of dr1
    val bookings = bookingService.viewBookingByDoctor(doctor1.id)

    bookings.forEach { println("Booking: Patient Id - ${it.patientId} Doctor ID - ${it.doctorId} Slot - ${it.slot}") }


    //try booking same slot with another patient
    try {
        val b2 = bookingService.book(p2.id, doctor1.id, "10:30")
    } catch (e: Exception) {
        println("Patient waitlisted ${e.message}")
    }

    bookingService.cancel(b1.id)


    //final bookings
    bookingService.viewBookingByDoctor(doctor1.id).forEach {
        println("Booking: Patient Id - ${it.patientId} Doctor ID - ${it.doctorId} Slot - ${it.slot}")
    }






}
