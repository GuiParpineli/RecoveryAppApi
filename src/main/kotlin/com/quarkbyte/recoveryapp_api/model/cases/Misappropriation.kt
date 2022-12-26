package com.quarkbyte.recoveryapp_api.model.cases

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonSubTypes
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
    id: UUID?,
    date: Date?,
    stepCSJ: StepCSJ?,
    resolutionDate: Date?,
    value: Double?,
    valueWithDiscount: Double?,
    coverageValue: Double?,
    resolutionType: ResolutionType?,
    recidivistCustomer: Boolean?,
    postCode: String?,
    var payMethod: PayMethod,
    var chargeBack: Boolean,
    var chargeBackDate: LocalDateTime
) : CaseCSJ(
    id,
    date,
    stepCSJ,
    resolutionDate,
    value,
    valueWithDiscount,
    coverageValue,
    resolutionType,
    recidivistCustomer,
    postCode,
) {
    override var typeCaseCSJ: String = "MISAPPROPRIATION"
}