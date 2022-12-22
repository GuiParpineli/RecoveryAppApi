package com.quarkbyte.recoveryapp_api.model.cases

import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

@Entity
abstract class CaseCSJ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    var date: Date

    @Enumerated(EnumType.STRING)
    var stepCSJ: StepCSJ
    var resolutionDate: Date
    var value: Double
    var valueWithDiscount: Double? = null
    var coverageValue: Double

    @Enumerated(EnumType.STRING)
    var resolutionType: ResolutionType
    var recidivistCustomer: Boolean

    @Enumerated(EnumType.STRING)
    var internalStatus: InternalStatus

    @Enumerated(EnumType.STRING)
    var externalStatus: ExternalStatus
    var postCode: String? = null

    constructor(
        id: UUID?,
        date: Date?,
        stepCSJ: StepCSJ?,
        resolutionDate: Date?,
        value: Double?,
        coverageValue: Double?,
        resolutionType: ResolutionType?,
        recidivistCustomer: Boolean?,
        internalStatus: InternalStatus?,
        externalStatus: ExternalStatus?,
    ) {
        this.id = id!!
        this.date = date!!
        this.stepCSJ = stepCSJ!!
        this.resolutionDate = resolutionDate!!
        this.value = value!!
        this.coverageValue = coverageValue!!
        this.resolutionType = resolutionType!!
        this.recidivistCustomer = recidivistCustomer!!
        this.internalStatus = internalStatus!!
        this.externalStatus = externalStatus!!
    }

    constructor(
        id: UUID?,
        date: Date?,
        stepCSJ: StepCSJ?,
        resolutionDate: Date?,
        value: Double?,
        valueWithDiscount: Double?,
        coverageValue: Double?,
        resolutionType: ResolutionType?,
        recidivistCustomer: Boolean?,
        internalStatus: InternalStatus?,
        externalStatus: ExternalStatus?,
        postCode: String?,
    ) {
        this.id = id!!
        this.date = date!!
        this.stepCSJ = stepCSJ!!
        this.resolutionDate = resolutionDate!!
        this.value = value!!
        this.valueWithDiscount = valueWithDiscount
        this.coverageValue = coverageValue!!
        this.recidivistCustomer = recidivistCustomer!!
        this.internalStatus = internalStatus!!
        this.externalStatus = externalStatus!!
        this.postCode = postCode
        this.resolutionType = resolutionType!!
    }
}
