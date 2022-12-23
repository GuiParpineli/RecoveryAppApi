package com.quarkbyte.recoveryapp_api.model.cases

import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.Entity
import java.util.*

@Entity
class TechnicalSupport : CaseCSJ {
    var repairValue: Double
    var status: Boolean

    constructor(
        id: UUID?, date: Date?, stepCSJ: StepCSJ?, typeCaseCSJ: TypeCaseCSJ,
        resolutionDate: Date?, value: Double?, valueWithDiscount: Double?,
        coverageValue: Double?, resolutionType: ResolutionType?,
        recidivistCustomer: Boolean?, internalStatus: InternalStatus?,
        externalStatus: ExternalStatus?, postCode: String?, repairValue: Double, status: Boolean
    ) : super(
        id, date, stepCSJ, typeCaseCSJ,resolutionDate, value, valueWithDiscount, coverageValue, resolutionType,
        recidivistCustomer!!, internalStatus, externalStatus, postCode
    ) {
        this.repairValue = repairValue
        this.status = status
    }

}