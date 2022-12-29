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
abstract class CaseCSJ {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: UUID?
    @DateTimeFormat(pattern = "dd-mm-yyyy") var date: Date
    @Enumerated(EnumType.STRING) var stepCSJ: StepCSJ
    var resolutionDate: Date?
    var value: Double
    var valueWithDiscount: Double? = 0.0
    var coverageValue: Double
    @Enumerated(EnumType.STRING) var resolutionType: ResolutionType?
    @Enumerated(EnumType.STRING) var internalStatus: InternalStatus
    @Enumerated(EnumType.STRING) var externalStatus: ExternalStatus
    @Column(unique = true) var postCode: String? = null
    abstract var typeCaseCSJ: String

    constructor(
        id: UUID? ,
        date: Date?,
        stepCSJ: StepCSJ?,
        resolutionDate: Date?,
        value: Double?,
        coverageValue: Double?,
        resolutionType: ResolutionType?,
    ) {
        this.id = id
        this.date = date!!
        this.stepCSJ = stepCSJ!!
        this.resolutionDate = resolutionDate!!
        this.value = value!!
        this.coverageValue = coverageValue!!
        this.resolutionType = resolutionType!!
        this.internalStatus = InternalStatus.CASO_NOVO
        this.externalStatus = ExternalStatus.EM_ABERTO
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
        postCode: String?,
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
        this.internalStatus = InternalStatus.CASO_NOVO
        this.externalStatus = ExternalStatus.EM_ABERTO
    }

}
