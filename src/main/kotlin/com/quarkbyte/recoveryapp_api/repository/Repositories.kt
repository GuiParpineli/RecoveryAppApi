package com.quarkbyte.recoveryapp_api.repository

import com.quarkbyte.recoveryapp_api.model.UserApp
import com.quarkbyte.recoveryapp_api.model.cases.CaseCSJ
import com.quarkbyte.recoveryapp_api.model.cases.Misappropriation
import com.quarkbyte.recoveryapp_api.model.cases.Sinistro
import com.quarkbyte.recoveryapp_api.model.cases.TechnicalSupport
import com.quarkbyte.recoveryapp_api.model.customer.Address
import com.quarkbyte.recoveryapp_api.model.customer.Bondsman
import com.quarkbyte.recoveryapp_api.model.customer.Customer
import com.quarkbyte.recoveryapp_api.model.plan.Plan
import com.quarkbyte.recoveryapp_api.model.plan.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AddressRepository : JpaRepository<Address, UUID>
@Repository
interface BondsmanRepository : JpaRepository<Bondsman, UUID>
@Repository
interface CaseRepository : JpaRepository<CaseCSJ, UUID>
@Repository
interface CustomerRepository : JpaRepository<Customer, UUID>
@Repository
interface MisappropriationRepository : JpaRepository<Misappropriation, UUID>
@Repository
interface PlanRepository : JpaRepository<Plan, UUID>
@Repository
interface ProductRepository : JpaRepository<Product, UUID>
@Repository
interface SinistroRepository : JpaRepository<Sinistro, UUID>
@Repository
interface TechnicalSupportRepository : JpaRepository<TechnicalSupport, UUID>

@Repository
interface UserRepository : JpaRepository<UserApp, UUID>
