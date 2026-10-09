package appointmentsystemflipmed.repository

import appointmentsystemflipmed.enums.Specialization
import appointmentsystemflipmed.model.Doctor
import java.util.UUID


class DoctorRepo {
    private val doctorMap = HashMap<UUID, Doctor>()

    fun save(doctor: Doctor) {
        doctorMap[doctor.id] = doctor
    }

    fun findById(id: UUID): Doctor? {
        return doctorMap[id]
    }

    fun findBySpecialization(specialization: Specialization): List<Doctor> {
        val res = ArrayList<Doctor>()
        doctorMap.values.forEach {
            if (it.specialization == specialization) {
                res.add(it)
            }
        }

        return res
    }
}
