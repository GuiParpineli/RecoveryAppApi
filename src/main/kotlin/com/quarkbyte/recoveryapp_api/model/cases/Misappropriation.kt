package com.quarkbyte.recoveryapp_api.model.cases

import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.Entity
import java.time.LocalDateTime
import java.util.*

@Entity
class Misappropriation : CaseCSJ {
    var payMethod: PayMethod
    var chargeBack: Boolean
    var chargeBackDate: LocalDateTime

    constructor(
        id: UUID?,
        date: Date?,
        stepCSJ: StepCSJ?,
        typeCaseCSJ: TypeCaseCSJ,
        resolutionDate: Date?,
        value: Double?,
        valueWithDiscount: Double?,
        coverageValue: Double?,
        resolutionType: ResolutionType?,
        recidivistCustomer: Boolean?,
        internalStatus: InternalStatus?,
        externalStatus: ExternalStatus?,
        postCode: String?,
        payMethod: PayMethod,
        chargeBack: Boolean,
        chargeBackDate: LocalDateTime
    ) : super(
        id,
        date,
        stepCSJ,
        typeCaseCSJ,
        resolutionDate,
        value,
        valueWithDiscount,
        coverageValue,
        resolutionType,
        recidivistCustomer,
        internalStatus,
        externalStatus,
        postCode,
    ) {
        this.payMethod = payMethod
        this.chargeBack = chargeBack
        this.chargeBackDate = chargeBackDate
    }
}