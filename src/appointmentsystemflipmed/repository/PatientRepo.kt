package appointmentsystemflipmed.repository

import appointmentsystemflipmed.model.Patient
import java.util.UUID


class PatientRepo {
    private val patientMap = HashMap<UUID, Patient>()

    fun save(patient: Patient) {
        patientMap[patient.id] = patient
    }

    fun findById(id: UUID): Patient? {
        return patientMap[id]
    }

}
