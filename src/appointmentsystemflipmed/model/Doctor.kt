package appointmentsystemflipmed.model

import appointmentsystemflipmed.enums.Specialization
import java.util.*
import kotlin.collections.HashMap

class Doctor(val name: String, val specialization: Specialization, val rating:Double) {
     val availability = HashMap<String, Boolean>()
     val id = UUID.randomUUID()


}
