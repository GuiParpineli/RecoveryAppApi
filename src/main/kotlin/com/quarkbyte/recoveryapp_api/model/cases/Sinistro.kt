package com.quarkbyte.recoveryapp_api.model.cases

import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.Entity
import java.util.*

@Entity
class Sinistro : CaseCSJ{
    var initialTime: Date
    var type: SinistroType
    var imeiStatus: Boolean
    var boStatus: Boolean
    var videoStatus: Boolean
    var sinistroDate: Date
    var franchise: Float
    var franchiseTotalValue: Double
    var discountValue: Double
    var payment: Boolean

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
        initialTime: Date,
        type: SinistroType,
        imeiStatus : Boolean,
        boStatus : Boolean,
        videoStatus: Boolean,
        sinistroDate: Date,
        franchise: Float,
        franchiseTotalValue : Double,
        discountValue : Double,
        payment: Boolean
    ) : super(
        id, date, stepCSJ, resolutionDate, value, coverageValue, resolutionType, recidivistCustomer,
        internalStatus, externalStatus
    ){
        this.initialTime = initialTime
        this.type = type
        this.imeiStatus = imeiStatus
        this.boStatus = boStatus
        this.videoStatus = videoStatus
        this.sinistroDate = sinistroDate
        this.franchise = franchise
        this.franchiseTotalValue = franchiseTotalValue
        this.discountValue = discountValue
        this.payment = payment
    }

}