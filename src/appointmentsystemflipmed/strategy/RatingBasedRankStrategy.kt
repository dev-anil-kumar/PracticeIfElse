package appointmentsystemflipmed.strategy

import appointmentsystemflipmed.dto.DoctorSlots
import appointmentsystemflipmed.utils.Utils

class RatingBasedRankStrategy : SlotRankingStrategy {
    override fun rank(slots: List<DoctorSlots>): List<DoctorSlots> {
        return slots.sortedByDescending { slot -> slot.doctor.rating }
    }
}
