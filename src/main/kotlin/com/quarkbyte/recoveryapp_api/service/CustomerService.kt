package com.quarkbyte.recoveryapp_api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.quarkbyte.recoveryapp_api.exceptions.DeleteErrorException
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.Customer
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
    @get:Throws(ResourceNotFoundException::class)
    val all: ResponseEntity<*>
        get() {
            val saved: List<Customer> = repository.findAll()
            val customerDTOS: MutableList<CustomerDTO> = ArrayList<CustomerDTO>()
            if (saved.isEmpty()) throw ResourceNotFoundException("None Customers founded")
            saved.forEach{ c: Customer? ->
                customerDTOS.add( mapper.convertValue( c, CustomerDTO::class.java )
                )
            }
            return ResponseEntity.ok<List<Customer>>(saved)
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
