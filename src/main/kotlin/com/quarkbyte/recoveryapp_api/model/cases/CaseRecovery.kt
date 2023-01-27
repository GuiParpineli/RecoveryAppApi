package com.quarkbyte.recoveryapp_api.model.cases

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.*
import lombok.NoArgsConstructor
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

@Entity
@NoArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = Misappropriation::class, name = "Misappropriation"),
    JsonSubTypes.Type(value = Sinistro::class, name = "Sinistro"),
    JsonSubTypes.Type(value = TechnicalSupport::class, name = "TechnicalSupport")
)
abstract class CaseRecovery {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: UUID?
    @DateTimeFormat(pattern = "dd-mm-yyyy") var date: Date
    @Enumerated(EnumType.STRING) var stepCSJ: StepCSJ
    var resolutionDate: Date?
    var value: Double
    var valueWithDiscount: Double? = 0.0
    var coverageValue: Double
    @Enumerated(EnumType.STRING) var resolutionType: ResolutionType?
    @Enumerated(EnumType.STRING) var internalStatus: InternalStatus = InternalStatus.CASO_NOVO
    @Enumerated(EnumType.STRING) var externalStatus: ExternalStatus = ExternalStatus.EM_ABERTO
    @Column(unique = true) var postCode: String? = null
    @ElementCollection var observation: MutableSet<String>? = null
    abstract var typeCaseCSJ: String

    constructor(
        id: UUID? ,
        date: Date?,
        stepCSJ: StepCSJ?,
        resolutionDate: Date?,
        value: Double?,
        coverageValue: Double?,
        observation: MutableSet<String>?,
        resolutionType: ResolutionType?,
        externalStatus: ExternalStatus,
        internalStatus: InternalStatus,
    ) {
        this.id = id
        this.date = date!!
        this.stepCSJ = stepCSJ!!
        this.resolutionDate = resolutionDate!!
        this.value = value!!
        this.coverageValue = coverageValue!!
        this.resolutionType = resolutionType!!
        this.observation = observation
        this.internalStatus = internalStatus
        this.externalStatus = externalStatus
    }

    constructor(
        id: UUID?,
        date: Date?,
        stepCSJ: StepCSJ?,
        resolutionDate: Date?,
        value: Double?,
        valueWithDiscount: Double?,
        coverageValue: Double?,
        postCode: String?,
        resolutionType: ResolutionType?,
        internalStatus: InternalStatus,
        externalStatus: ExternalStatus,
        observation: MutableSet<String>?,
    ) {
        this.id = id
        this.date = date!!
        this.stepCSJ = stepCSJ!!
        this.resolutionDate = resolutionDate
        this.value = value!!
        this.valueWithDiscount = valueWithDiscount
        this.coverageValue = coverageValue!!
        this.postCode = postCode
        this.resolutionType = resolutionType
        this.internalStatus = internalStatus
        this.externalStatus = externalStatus
        this.observation = observation
    }

}
