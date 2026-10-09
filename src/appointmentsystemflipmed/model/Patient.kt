package appointmentsystemflipmed.model

import appointmentsystemflipmed.enums.Specialization
import java.util.UUID


class Patient(val name: String) {
    val id = UUID.randomUUID()
}
