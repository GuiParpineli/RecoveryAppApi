package com.quarkbyte.recoveryapp_api

import com.quarkbyte.recoveryapp_api.model.*
import com.quarkbyte.recoveryapp_api.model.cases.Misappropriation
import com.quarkbyte.recoveryapp_api.model.cases.Sinistro
import com.quarkbyte.recoveryapp_api.model.cases.TechnicalSupport
import com.quarkbyte.recoveryapp_api.model.customer.Address
import com.quarkbyte.recoveryapp_api.model.customer.Bondsman
import com.quarkbyte.recoveryapp_api.model.customer.Customer
import com.quarkbyte.recoveryapp_api.model.enums.Gender
import com.quarkbyte.recoveryapp_api.model.enums.StatusProduct
import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import com.quarkbyte.recoveryapp_api.model.plan.Plan
import com.quarkbyte.recoveryapp_api.model.plan.Product
import com.quarkbyte.recoveryapp_api.repository.*
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class DataLoader(
    private val customerRepository: CustomerRepository,
    private val bondsmanRepository: BondsmanRepository,
    private val addressRepository: AddressRepository,
    private val productRepository: ProductRepository,
    private val planRepository: PlanRepository,
    private val sinistroRepository: SinistroRepository,
    private val technicalSupportRepository: TechnicalSupportRepository,
    private val misappropriationRepository: MisappropriationRepository,
    private val userRepository: UserRepository
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {

//        //save products
            productRepository.save(
                Product(
                    null,
                    "Iphone 13 Pro",
                    StatusProduct.ADQUIRIDO,
                    "22345",
                    "999929-229",
                    80000.0
                )
            )
            productRepository.save(
                Product(
                    null,
                    "AppleWatch 5",
                    StatusProduct.ADQUIRIDO,
                    "3sa151345",
                    "888929-229",
                    50000.0
                )
            )
            //save address
            addressRepository.save(
                Address(
                    null,
                    "Berilo",
                    "MG",
                    "Ruas das flores",
                    "Brasil",
                    "1231-3123",
                    ""
                )
            )

            //saving customers
            customerRepository.save(
                Customer(
                    null,
                    "Alberto",
                    "Robson",
                    "123.123.134-22",
                    "9931-1231",
                    addressRepository.findAll()[0],
                    "albert@gmail.com",
                    Date(),
                    Gender.MASCULINO,
                    "Uganda"
                )
            )
            customerRepository.save(
                Customer(
                    null,
                    "Jorge",
                    "Ventura",
                    "343.123.134-22",
                    "32931-1231",
                    addressRepository.findAll()[0],
                    "jorge@gmail.com",
                    Date(),
                    Gender.MASCULINO,
                    "Jamaica"
                )
            )
            customerRepository.save(
                Customer(
                    null,
                    "Zico",
                    "Costa",
                    "213.222.123-22",
                    "99931-1231",
                    addressRepository.findAll()[0],
                    "jorge@gmail.com",
                    Date(),
                    Gender.MASCULINO,
                    "Jamaica"
                )
            )

            //saving Bondsman
            bondsmanRepository.save(
                Bondsman(
                    null,
                    "Zico",
                    "Costa",
                    "213.222.123-22",
                    "99931-1231",
                    addressRepository.findAll()[0],
                    "jorge@gmail.com",
                    Date(),
                    Gender.MASCULINO,
                    "Jamaica"
                )
            )

            //save plantypes
            sinistroRepository.save(
                Sinistro(
                    UUID.randomUUID(), Date(), StepCSJ.ACORDO, TypeCaseCSJ.SINISTRO, Date(),
                    2000.0, 20.0, ResolutionType.CHARGEBACK_PAGO, false,
                    InternalStatus.ACOMPANHAR, ExternalStatus.EM_ABERTO,
                    Date(), SinistroType.FURTO_QUALIFICADO,
                    true, true,
                    true, Date(), 200.0f,
                    2222.0, 20.0, false
                )
            )

            misappropriationRepository.save(
                Misappropriation(
                    UUID.randomUUID(), Date(), StepCSJ.EXTRAJUDICIAL,TypeCaseCSJ.MISAPPROPRIATION, Date(), 5000.0, 4500.0, 4000.0,
                    ResolutionType.RECORRENCIA_PAGA, true, InternalStatus.CASO_NOVO,
                    ExternalStatus.EM_ABERTO, "PAC213213BR", PayMethod.NORMAL, false,
                    LocalDateTime.now()
                )
            )
            technicalSupportRepository.save(
                TechnicalSupport(
                    UUID.randomUUID(), Date(), StepCSJ.ACORDO, TypeCaseCSJ.TECHNICAL_SUPPORT,Date(), 12000.0, 10000.0,
                    90000.0, ResolutionType.COMPRA, false, InternalStatus.PAGO,
                    ExternalStatus.RECUPERADO, "2223201BR", 25000.0, true
                )
            )

            //save users
            userRepository.save(
                UserApp(null, "Paulera")
            )

            //save plan
            planRepository.save(
                Plan(
                    UUID.randomUUID(),
                    5000.00,
                    true,
                    userRepository.findAll()[0],
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    listOf(productRepository.findAll()[0],
                        productRepository.findAll()[1]
                    ),
                    customerRepository.findAll()[0],
                    bondsmanRepository.findAll()[0],
                    sinistroRepository.findAll()[0]
                )
            )
            planRepository.save(
                Plan(
                    UUID.randomUUID(),
                    10000.00,
                    true,
                    userRepository.findAll()[0],
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                       listOf(productRepository.findAll()[0],
                        productRepository.findAll()[1]
                    ),
                    customerRepository.findAll()[1],
                    bondsmanRepository.findAll()[0],
                    misappropriationRepository.findAll()[0]
                )
            )
        planRepository.save(
                Plan(
                    UUID.randomUUID(),
                    20000.00,
                    true,
                    userRepository.findAll()[0],
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                       listOf(productRepository.findAll()[0],
                        productRepository.findAll()[1],
                        productRepository.findAll()[1]
                    ),
                    customerRepository.findAll()[1],
                    bondsmanRepository.findAll()[0],
                    misappropriationRepository.findAll()[0]
                )
            )
        planRepository.save(
                Plan(
                    UUID.randomUUID(),
                    6000.00,
                    true,
                    userRepository.findAll()[0],
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                       listOf(productRepository.findAll()[0]
                    ),
                    customerRepository.findAll()[2],
                    bondsmanRepository.findAll()[0],
                    technicalSupportRepository.findAll()[0]
                )
            )


//
        }
    }

