package com.quarkbyte.recoveryapp_api.model.cases

import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

@Entity
abstract class CaseCSJ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: UUID

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private var date: Date

    @Enumerated(EnumType.STRING)
    private var stepCSJ: StepCSJ
    private var resolutionDate: Date
    private var value: Double
    private var valueWithDiscount: Double? = null
    private var coverageValue: Double

    @Enumerated(EnumType.STRING)
    private lateinit var resolutionType: ResolutionType
    private var recidivistCustomer: Boolean

    @Enumerated(EnumType.STRING)
    private var internalStatus: InternalStatus

    @Enumerated(EnumType.STRING)
    private var externalStatus: ExternalStatus
    private var postCode: String? = null

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
        externalStatus: ExternalStatus?
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
        recidivistCustomer: Boolean,
        internalStatus: InternalStatus?,
        externalStatus: ExternalStatus?,
        postCode: String?
    ) {
        this.id = id!!
        this.date = date!!
        this.stepCSJ = stepCSJ!!
        this.resolutionDate = resolutionDate!!
        this.value = value!!
        this.valueWithDiscount = valueWithDiscount
        this.coverageValue = coverageValue!!
        this.recidivistCustomer = recidivistCustomer
        this.internalStatus = internalStatus!!
        this.externalStatus = externalStatus!!
        this.postCode = postCode
    }
}
