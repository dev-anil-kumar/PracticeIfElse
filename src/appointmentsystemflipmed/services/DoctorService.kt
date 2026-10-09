package appointmentsystemflipmed.services

import appointmentsystemflipmed.enums.Specialization
import appointmentsystemflipmed.model.Doctor
import appointmentsystemflipmed.model.DoctorNotFoundException
import appointmentsystemflipmed.model.Patient
import appointmentsystemflipmed.repository.DoctorRepo
import java.util.UUID

class DoctorService(val repo: DoctorRepo) {
    fun register(name: String, specialization: Specialization, rating: Double): Doctor {
        val doctor = Doctor(name, specialization, rating)
        repo.save(doctor)
        return doctor
    }

    fun markDoctorAvailability(doctorId: UUID, slots: List<String>) {
        val doctor = repo.findById(doctorId) ?: throw DoctorNotFoundException("doctor not found")

        slots.forEach {
            doctor.availability[it] = true
        }
    }


}
