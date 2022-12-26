package com.quarkbyte.recoveryapp_api.model.cases

import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

@Entity
abstract class CaseCSJ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    var date: Date

    @Enumerated(EnumType.STRING)
    var stepCSJ: StepCSJ

    @Enumerated(EnumType.STRING)
    var typeCaseCSJ: TypeCaseCSJ
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
    @Column(unique = true)
    var postCode: String? = null

    constructor(
        id: UUID?,
        date: Date,
        stepCSJ: StepCSJ,
        typeCaseCSJ: TypeCaseCSJ,
        resolutionDate: Date,
        value: Double,
        coverageValue: Double,
        resolutionType: ResolutionType,
        recidivistCustomer: Boolean,
    ) {
        this.id = id
        this.date = date
        this.stepCSJ = stepCSJ
        this.typeCaseCSJ = typeCaseCSJ
        this.resolutionDate = resolutionDate
        this.value = value
        this.coverageValue = coverageValue
        this.resolutionType = resolutionType
        this.recidivistCustomer = recidivistCustomer
        this.internalStatus = InternalStatus.CASO_NOVO
        this.externalStatus = ExternalStatus.EM_ABERTO
    }

    constructor(
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
    ) {
        this.id = id
        this.date = date
        this.stepCSJ = stepCSJ
        this.typeCaseCSJ = typeCaseCSJ
        this.resolutionDate = resolutionDate
        this.value = value
        this.valueWithDiscount = valueWithDiscount
        this.coverageValue = coverageValue
        this.recidivistCustomer = recidivistCustomer
        this.postCode = postCode
        this.resolutionType = resolutionType
        this.internalStatus = InternalStatus.CASO_NOVO
        this.externalStatus = ExternalStatus.EM_ABERTO
    }
}
