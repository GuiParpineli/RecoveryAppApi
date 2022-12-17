package com.quarkbyte.recoveryapp_api.model

import com.quarkbyte.recoveryapp_api.model.enums.csjSINISTRO.SinistroType
import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import jakarta.persistence.*
import java.util.Date

@Entity
data class CaseCJS(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val date: Date,
    @Enumerated(EnumType.STRING)
    var status: StatusCSJ,
    var analyst: String,
    @Enumerated(EnumType.STRING)
    var step: StepCSJ,
    var resolutionDate: Date,
    var value: Double,
    var covarageValue: Double,
    @Enumerated(EnumType.STRING)
    var resolutionTpe: ResolutionType,
    var recidivistCustomer: Boolean,
    @Enumerated(EnumType.STRING)
    var internalStatus: InternalStatus,
    @Enumerated(EnumType.STRING)
    var externalStatus: ExternalStatus,

    var initialTime: Date?,
    @Enumerated(EnumType.STRING)
    var sinistroType: SinistroType?,
    var franchise: Float?,
    var franchiseValue: Double?,
    var discountValue: Double?
) {
    fun isSinitro(
        initialTime: Date,
        sinistroType: SinistroType?,
        franchise: Float,
        franchiseValue: Double,
        discountValue: Double
    ) {
            //vals for status = SINISTRO
        if (this.status == StatusCSJ.SINISTRO) {
            this.initialTime = initialTime
            this.sinistroType = sinistroType
            this.franchise = franchise
            this.franchiseValue = franchiseValue
            this.discountValue = discountValue
        }
    }
}


