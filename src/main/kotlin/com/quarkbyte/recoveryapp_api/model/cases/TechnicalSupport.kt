package com.quarkbyte.recoveryapp_api.model.cases

import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.Entity
import java.util.*

@Entity
class TechnicalSupport(
    id: UUID?,
    date: Date,
    stepCSJ: StepCSJ,
    typeCaseCSJ: TypeCaseCSJ,
    resolutionDate: Date,
    value: Double,
    valueWithDiscount: Double,
    coverageValue: Double,
    resolutionType: ResolutionType,
    recidivistCustomer: Boolean,
    postCode: String,
    var repairValue: Double,
    var status: Boolean
) : CaseCSJ(
    id, date, stepCSJ, typeCaseCSJ, resolutionDate, value, valueWithDiscount, coverageValue, resolutionType,
    recidivistCustomer, postCode
)
