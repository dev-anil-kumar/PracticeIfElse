package appointmentsystemflipmed.services

import appointmentsystemflipmed.model.Patient
import appointmentsystemflipmed.model.PatientNotFoundException
import appointmentsystemflipmed.repository.PatientRepo
import java.util.*

class PatientService(val repo: PatientRepo) {
    fun register(name: String): Patient {
        val patient = Patient(name)
        repo.save(patient)
        return patient
    }

    fun findById(id: UUID): Patient? {
        val patient = repo.findById(id) ?: throw PatientNotFoundException("Patient not Found")

        return patient
    }
}
