package appointmentsystemflipmed.dto

import appointmentsystemflipmed.model.Doctor
import java.util.UUID

data class DoctorSlots(val doctor: Doctor, val slot: String)
