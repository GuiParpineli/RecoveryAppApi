package com.quarkbyte.recoveryapp_api.model.cases

import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.Entity
import java.util.*

@Entity
class Sinistro : CaseCSJ{
    private var initialTime: Date
    private var type: SinistroType
    private var imeiStatus: Boolean
    private var boStatus: Boolean
    private var videoStatus: Boolean
    private var sinistroDate: Date
    private var franchise: Float
    private var franchiseTotalValue: Double
    private var discountValue: Double
    private var payment: Boolean

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