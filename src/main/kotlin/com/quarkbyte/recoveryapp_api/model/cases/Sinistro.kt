package com.quarkbyte.recoveryapp_api.model.cases

import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.Entity
import java.util.*

@Entity
class Sinistro(
    id: UUID?,
    date: Date?,
    stepCSJ: StepCSJ?,
    typeCaseCSJ: TypeCaseCSJ,
    resolutionDate: Date?,
    value: Double?,
    coverageValue: Double?,
    resolutionType: ResolutionType?,
    recidivistCustomer: Boolean?,
    var initialTime: Date,
    var type: SinistroType,
    var imeiStatus: Boolean,
    var boStatus: Boolean,
    var videoStatus: Boolean,
    var sinistroDate: Date,
    var franchise: Float,
    var franchiseTotalValue: Double,
    var discountValue: Double,
    var payment: Boolean,
) : CaseCSJ(
    id, date, stepCSJ, typeCaseCSJ, resolutionDate, value, coverageValue,
    resolutionType, recidivistCustomer,
)

