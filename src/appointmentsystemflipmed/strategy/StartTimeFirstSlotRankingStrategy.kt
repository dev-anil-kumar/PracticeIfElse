package appointmentsystemflipmed.strategy

import appointmentsystemflipmed.dto.DoctorSlots
import appointmentsystemflipmed.utils.Utils

class StartTimeFirstSlotRankingStrategy : SlotRankingStrategy {
    override fun rank(slots: List<DoctorSlots>): List<DoctorSlots> {
        return slots.sortedBy { Utils.convertStringToLocalTime(it.slot) }
    }
}
