package appointmentsystemflipmed.strategy

import appointmentsystemflipmed.dto.DoctorSlots

interface SlotRankingStrategy {
    fun rank(slots: List<DoctorSlots>) : List<DoctorSlots>

}
