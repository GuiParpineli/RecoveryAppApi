package com.quarkbyte.recoveryapp_api.repository

import com.quarkbyte.recoveryapp_api.model.cases.CaseCSJ
import com.quarkbyte.recoveryapp_api.model.cases.Misappropriation
import com.quarkbyte.recoveryapp_api.model.cases.Sinistro
import com.quarkbyte.recoveryapp_api.model.cases.TechnicalSupport
import com.quarkbyte.recoveryapp_api.model.customer.Address
import com.quarkbyte.recoveryapp_api.model.customer.Bondsman
import com.quarkbyte.recoveryapp_api.model.customer.Customer
import com.quarkbyte.recoveryapp_api.model.plan.Plan
import com.quarkbyte.recoveryapp_api.model.plan.Product
import com.quarkbyte.recoveryapp_api.model.user.Scheduler
import com.quarkbyte.recoveryapp_api.model.user.Tasks
import com.quarkbyte.recoveryapp_api.model.user.UserApp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AddressRepository : JpaRepository<Address, UUID>

@Repository
interface BondsmanRepository : JpaRepository<Bondsman, UUID> {
    fun findByEmailOrCpf(email: String, cpf: String): Bondsman?
    fun findByNameContainingIgnoreCase(name: String): List<Bondsman>?
}

@Repository
interface CustomerRepository : JpaRepository<Customer, UUID> {
    fun findByEmailOrCpf(email: String, cpf: String): Customer?
    fun findByNameContainingIgnoreCase(name: String): List<Bondsman>?
}

@Repository
interface CaseRepository : JpaRepository<CaseCSJ, UUID>

@Repository
interface MisappropriationRepository : JpaRepository<Misappropriation, UUID>

@Repository
interface PlanRepository : JpaRepository<Plan, UUID> {
    fun findPlanByCode(code: String): Plan?
    fun findByCustomer(customer: Customer): Plan?
    fun findByAnalyst_Email(email: String): Plan?
    fun findByBondsman(bondsman: Bondsman): Plan?
}

@Repository
interface ProductRepository : JpaRepository<Product, UUID>

@Repository
interface SinistroRepository : JpaRepository<Sinistro, UUID>

@Repository
interface TechnicalSupportRepository : JpaRepository<TechnicalSupport, UUID>

@Repository
interface UserRepository : JpaRepository<UserApp, UUID> {
    fun findByUsername(username: String): UserApp
}

@Repository
interface ScheduleRepository : JpaRepository<Scheduler, UUID>

@Repository
interface TasksRepository : JpaRepository<Tasks, UUID>