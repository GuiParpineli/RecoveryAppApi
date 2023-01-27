package com.quarkbyte.recoveryapp_api.model.cases

import com.fasterxml.jackson.annotation.JsonTypeName
import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.Entity
import java.util.*

@Entity
@JsonTypeName("TechnicalSupport")
class TechnicalSupport(
    id: UUID? = null,
    date: Date?,
    stepCSJ: StepCSJ?,
    resolutionDate: Date?,
    value: Double?,
    valueWithDiscount: Double?,
    coverageValue: Double?,
    resolutionType: ResolutionType?,
    postCode: String?,
    observation: MutableSet<String>?= null,
    internalStatus: InternalStatus ,
    externalStatus: ExternalStatus ,
    var repairValue: Double = 0.0,
    var status: Boolean = true
) : CaseRecovery(
    id, date, stepCSJ, resolutionDate, value, valueWithDiscount, coverageValue, postCode, resolutionType,
    internalStatus, externalStatus , observation
) {
    override var typeCaseCSJ: String = "TECHNICALSUPPORT"
}
