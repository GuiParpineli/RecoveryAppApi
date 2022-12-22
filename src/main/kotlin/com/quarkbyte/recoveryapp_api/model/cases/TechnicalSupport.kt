package com.quarkbyte.recoveryapp_api.model.cases

import com.quarkbyte.recoveryapp_api.model.enums.csj.ExternalStatus
import com.quarkbyte.recoveryapp_api.model.enums.csj.InternalStatus
import com.quarkbyte.recoveryapp_api.model.enums.csj.ResolutionType
import com.quarkbyte.recoveryapp_api.model.enums.csj.StepCSJ
import jakarta.persistence.Entity
import java.util.*

@Entity
class TechnicalSupport : CaseCSJ {
    private var repairValue: Double
    private var status: Boolean

    constructor(
        id: UUID?, date: Date?, stepCSJ: StepCSJ?,
        resolutionDate: Date?, value: Double?, valueWithDiscount: Double?,
        coverageValue: Double?, resolutionType: ResolutionType?,
        recidivistCustomer: Boolean?, internalStatus: InternalStatus?,
        externalStatus: ExternalStatus?, postCode: String?, repairValue: Double, status: Boolean
    ) : super(
        id, date, stepCSJ, resolutionDate, value, valueWithDiscount, coverageValue, resolutionType,
        recidivistCustomer!!, internalStatus, externalStatus, postCode
    ) {
        this.repairValue = repairValue
        this.status = status
    }

}