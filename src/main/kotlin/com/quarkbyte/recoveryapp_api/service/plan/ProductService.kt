package com.quarkbyte.recoveryapp_api.service.plan

import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.plan.Product
import com.quarkbyte.recoveryapp_api.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(private val repository: ProductRepository) {

    fun getAll(): ResponseEntity<*> {
        val saved: MutableList<Product?> = repository.findAll()
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved: Product? = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None products founded") }
        return ResponseEntity.ok<Any>(saved)
    }

    @Throws(SaveErrorException::class)
    fun save(customer: Product): ResponseEntity<*> {
        val saved: Product
        saved = try {
            repository.save(customer)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok<Any>(saved)
    }

    fun getAllbyId(id: List<UUID>): ResponseEntity<List<Product>> {
        val saved: List<Product> = repository.findAllById(id)
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun update(customer: Product): ResponseEntity<*> {
        val saved: Product
        saved = try {
            repository.saveAndFlush(customer)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok<Any>(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        val saved: Product? = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None products founded") }
        repository.deleteById(id)
        return ResponseEntity.ok<String>(" deleted successfully")
    }
}