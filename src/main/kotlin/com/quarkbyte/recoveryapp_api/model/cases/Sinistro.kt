package com.quarkbyte.recoveryapp_api.model.cases

import com.fasterxml.jackson.annotation.JsonTypeName
import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.Entity
import lombok.NoArgsConstructor
import java.util.*

@Entity
@NoArgsConstructor
@JsonTypeName("Sinistro")
class Sinistro(
    id: UUID? = null,
    date: Date?,
    stepCSJ: StepCSJ?,
    resolutionDate: Date?,
    value: Double?,
    observation: MutableSet<String>?= null,
    coverageValue: Double?,
    resolutionType: ResolutionType?,
    internalStatus: InternalStatus,
    externalStatus: ExternalStatus,
    var initialTime: Date,
    var sinistroType: SinistroType,
    var imeiStatus: Boolean,
    var boStatus: Boolean,
    var videoStatus: Boolean,
    var sinistroDate: Date,
    var franchise: Float,
    var franchiseTotalValue: Double,
    var discountValue: Double,
    var payment: Boolean,
) : CaseRecovery(
    id, date, stepCSJ, resolutionDate, value, coverageValue, observation,
    resolutionType, externalStatus, internalStatus
) {
    override var typeCaseCSJ: String = "SINISTRO"
}

