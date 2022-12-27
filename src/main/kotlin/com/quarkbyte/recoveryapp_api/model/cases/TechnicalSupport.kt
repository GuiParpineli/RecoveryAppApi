package com.quarkbyte.recoveryapp_api.model.cases

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.Entity
import lombok.NoArgsConstructor
import java.util.*

@Entity
@JsonTypeName("TechnicalSupport")
class TechnicalSupport(
    id: UUID? = UUID.randomUUID(),
    date: Date?,
    stepCSJ: StepCSJ?,
    resolutionDate: Date?,
    value: Double?,
    valueWithDiscount: Double?,
    coverageValue: Double?,
    resolutionType: ResolutionType?,
    recidivistCustomer: Boolean?,
    postCode: String?,
    var repairValue: Double = 0.0,
    var status: Boolean = true
) : CaseCSJ(
    id, date, stepCSJ,  resolutionDate, value, valueWithDiscount, coverageValue, resolutionType,
    recidivistCustomer!!,  postCode
){
    override var typeCaseCSJ: String = "TECHNICALSUPPORT"
}
