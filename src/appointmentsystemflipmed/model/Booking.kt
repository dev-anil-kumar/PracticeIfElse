package appointmentsystemflipmed.model

import appointmentsystemflipmed.enums.Specialization
import java.util.UUID
import kotlin.uuid.Uuid


class Booking(val patientId: UUID, val doctorId: UUID, val slot: String) {
    val id = UUID.randomUUID()
}
