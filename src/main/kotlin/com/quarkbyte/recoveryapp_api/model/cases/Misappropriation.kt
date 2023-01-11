package com.quarkbyte.recoveryapp_api.model.cases

import com.fasterxml.jackson.annotation.JsonTypeName
import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.Entity
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import java.util.*

@Entity
@NoArgsConstructor
@JsonTypeName("Misappropriation")
class Misappropriation(
    id: UUID? = null,
    date: Date,
    stepCSJ: StepCSJ,
    resolutionDate: Date?,
    value: Double,
    valueWithDiscount: Double,
    coverageValue: Double,
    resolutionType: ResolutionType?,
    postCode: String?,
    observation: MutableList<String>?= null,
    internalStatus: InternalStatus ,
    externalStatus: ExternalStatus ,
    var payMethod: PayMethod,
    var chargeBack: Boolean,
    var chargeBackDate: Date
) : CaseCSJ(
    id,
    date,
    stepCSJ,
    resolutionDate,
    value,
    valueWithDiscount,
    coverageValue,
    postCode,
    resolutionType,
    internalStatus,
    externalStatus,
    observation,
) {
    override var typeCaseCSJ: String = "MISAPPROPRIATION"

}