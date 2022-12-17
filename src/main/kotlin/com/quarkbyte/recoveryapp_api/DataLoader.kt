package com.quarkbyte.recoveryapp_api

import com.quarkbyte.recoveryapp_api.model.Customer
import com.quarkbyte.recoveryapp_api.model.enums.Gender
import com.quarkbyte.recoveryapp_api.repository.CustomerRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class DataLoader(private val repository: CustomerRepository) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        repository.save(
            Customer(
                0,
                "Alberto",
                "Robson",
                "453.232.424-12",
                "1234123",
                "alb@gmail.com",
                Date(),
                Gender.MASCULINO,
                "Gana",
            )
        )
        repository.save(
            Customer(
                0,
                "Marie",
                "Currie",
                "123.232.424-12",
                "2314123",
                "marie@gmail.com",
                Date(),
                Gender.FEMININO,
                "French",
            )
        )
    }
}