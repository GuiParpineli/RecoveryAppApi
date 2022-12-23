package com.quarkbyte.recoveryapp_api.service.customer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.customer.Customer
import com.quarkbyte.recoveryapp_api.model.dto.CustomerDTO
import com.quarkbyte.recoveryapp_api.repository.CustomerRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
data class CustomerService(
    val repository: CustomerRepository,
    val mapper: ObjectMapper
) {
    @Throws(ResourceNotFoundException::class)
    fun getAll(): ResponseEntity<Any> {
        val saved: List<Customer> = repository.findAll()
        if (saved.isEmpty()) throw ResourceNotFoundException("None Customers founded")
        val customerDTOS: MutableList<CustomerDTO> = mutableListOf()
        saved.forEach { c -> customerDTOS.add( mapper.convertValue(c) ) }
        return ResponseEntity.ok(customerDTOS)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved: Customer = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None address founded") }
        return ResponseEntity.ok<Any>(saved)
    }

    @Throws(SaveErrorException::class)
    fun save(customer: Customer): ResponseEntity<*> {
        val saved: Customer = try {
            repository.save(customer)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok<Any>(saved)
    }

    @Throws(SaveErrorException::class)
    fun update(customer: Customer): ResponseEntity<*> {
        val saved: Customer = try {
            repository.saveAndFlush(customer)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok<Any>(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        val saved: Customer = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None address founded") }
        repository.deleteById(id)
        return ResponseEntity.ok<String>("$saved deleted successfully")
    }
}
