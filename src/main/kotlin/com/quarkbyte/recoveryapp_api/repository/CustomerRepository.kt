package com.quarkbyte.recoveryapp_api.repository

import com.quarkbyte.recoveryapp_api.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long>
