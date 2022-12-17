package com.quarkbyte.recoveryapp_api.service

import com.quarkbyte.recoveryapp_api.exceptions.DeleteErrorException
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.Customer
import com.quarkbyte.recoveryapp_api.repository.CustomerRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
data class CustomerService(
    val repository: CustomerRepository
) {
    fun getAll(): ResponseEntity<Any> {
        val customers: List<Customer> = repository.findAll()
        return ResponseEntity.ok(customers)
    }

    fun getById(id: Long): ResponseEntity<Any> {
        val customer: Customer = repository.findById(id).orElseThrow { ResourceNotFoundException("User not found") }
        return ResponseEntity.ok(customer)
    }

    fun save(customer: Customer): ResponseEntity<Any> {
        val saved: Customer
        try {
            saved = repository.save(customer)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved  error: $e")
        }
        return ResponseEntity.ok(saved)
    }

    fun deleteById(id: Long): ResponseEntity<Any> {
        try {
            repository.deleteById(repository.findById(id).get().id!!)
        } catch (e: Exception) {
            throw DeleteErrorException("Error, not deleted | Error: $e")
        }
        return ResponseEntity.ok("Deleted successfully")
    }

    fun update(customer: Customer) : ResponseEntity<Any>{
      TODO()
    }
}
