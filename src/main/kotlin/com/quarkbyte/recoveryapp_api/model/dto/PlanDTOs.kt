package com.quarkbyte.recoveryapp_api.model.dto
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.quarkbyte.recoveryapp_api.model.cases.CaseCSJ
import com.quarkbyte.recoveryapp_api.model.plan.Product
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlanDTOs(
    var id: UUID?,
    var planStatus: Boolean,
    var code: String,
    var value: Double,
    var user: UserDTO,
    var initialDate: Date,
    var finalDate: Date?,
    var productList: List<Product>,
    var customer: CustomerDTO,
    var bondsman: BondsmanDTO,
    var caseCSJ: List<CaseCSJ?>,
    var recidivistCustomer: Boolean?,
    var creationDate: Date
) {
}