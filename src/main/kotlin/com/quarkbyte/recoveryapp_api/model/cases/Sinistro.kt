package com.quarkbyte.recoveryapp_api.model.cases

import com.fasterxml.jackson.annotation.JsonTypeName
import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.Entity
import lombok.NoArgsConstructor
import java.util.*

@Entity
@NoArgsConstructor
@JsonTypeName("Sinistro")
class Sinistro(
    id: UUID?,
    date: Date?,
    stepCSJ: StepCSJ?,
    resolutionDate: Date?,
    value: Double?,
    coverageValue: Double?,
    resolutionType: ResolutionType?,
    recidivistCustomer: Boolean?,
    var initialTime: Date,
    var sinistroType: SinistroType,
    var imeiStatus: Boolean,
    var boStatus: Boolean,
    var videoStatus: Boolean,
    var sinistroDate: Date,
    var franchise: Float,
    var franchiseTotalValue: Double,
    var discountValue: Double,
    var payment: Boolean,
) : CaseCSJ(
    id, date, stepCSJ,  resolutionDate, value, coverageValue,
    resolutionType, recidivistCustomer,
){
    override var typeCaseCSJ: String = "SINISTRO"
}

