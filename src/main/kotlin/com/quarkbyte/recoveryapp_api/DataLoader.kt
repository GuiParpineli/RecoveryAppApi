package com.quarkbyte.recoveryapp_api

import com.quarkbyte.recoveryapp_api.model.cases.Misappropriation
import com.quarkbyte.recoveryapp_api.model.cases.Sinistro
import com.quarkbyte.recoveryapp_api.model.cases.TechnicalSupport
import com.quarkbyte.recoveryapp_api.model.customer.Address
import com.quarkbyte.recoveryapp_api.model.customer.Bondsman
import com.quarkbyte.recoveryapp_api.model.customer.Customer
import com.quarkbyte.recoveryapp_api.model.enums.Gender
import com.quarkbyte.recoveryapp_api.model.enums.StatusProduct
import com.quarkbyte.recoveryapp_api.model.enums.SystemUserRoles
import com.quarkbyte.recoveryapp_api.model.enums.csj.*
import com.quarkbyte.recoveryapp_api.model.plan.Plan
import com.quarkbyte.recoveryapp_api.model.plan.Product
import com.quarkbyte.recoveryapp_api.model.user.Scheduler
import com.quarkbyte.recoveryapp_api.model.user.Tasks
import com.quarkbyte.recoveryapp_api.model.user.UserApp
import com.quarkbyte.recoveryapp_api.repository.*
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
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
    private val userRepository: UserRepository,
    private val scheduleRepository: ScheduleRepository,
    private val tasksRepository: TasksRepository
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {

        val bCryptPasswordEncoder = BCryptPasswordEncoder();

//        //save products
        productRepository.save(
            Product(
                null,
                "Iphone 13 Pro",
                StatusProduct.ADQUIRIDO,
                "22345",
                "999929-229",
                8000.0
            )
        )
        productRepository.save(
            Product(
                null,
                "Iphone 9",
                StatusProduct.ADQUIRIDO,
                "22345",
                "999929-229",
                3000.0
            )
        )
        productRepository.save(
            Product(
                null,
                "AppleWatch 5",
                StatusProduct.ADQUIRIDO,
                "3sa151345",
                "888929-229",
                5000.0
            )
        )
        //save address
        addressRepository.save(
            Address(
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
                "66 9931-1231",
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
                "13 32931-1231",
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
                "12 76931-1231",
                addressRepository.findAll()[0],
                "zico@gmail.com",
                Date(),
                Gender.MASCULINO,
                "Jamaica"
            )
        )
        customerRepository.save(
            Customer(
                null,
                "Cristian",
                "Simsen",
                "321.162.123-22",
                "21 9931-1231",
                addressRepository.findAll()[0],
                "csimsen@gmail.com",
                Date(),
                Gender.MASCULINO,
                "Brasil"
            )
        )

        //saving Bondsman
        bondsmanRepository.save(
            Bondsman(
                null,
                "Zico",
                "Costa",
                "213.222.123-22",
                "22 99931-1231",
                addressRepository.findAll()[0],
                "jorge@gmail.com",
                Date(),
                Gender.MASCULINO,
                "Jamaica"
            )
        )
        bondsmanRepository.save(
            Bondsman(
                null,
                "Osvaldo",
                "Gomes",
                "13.222.123-22",
                "19 4231-1231",
                addressRepository.findAll()[0],
                "osvald@gmail.com",
                Date(),
                Gender.MASCULINO,
                "Letonia"
            )
        )
        bondsmanRepository.save(
            Bondsman(
                null,
                "Juricleide",
                "Costa",
                "786.231.423-22",
                "91931-1231",
                addressRepository.findAll()[0],
                "jure@gmail.com",
                Date(),
                Gender.OUTRO,
                "Suriname"
            )
        )
        bondsmanRepository.save(
            Bondsman(
                null,
                "Maria",
                "Silva",
                "676.431.123-22",
                "81931-1231",
                addressRepository.findAll()[0],
                "maria@gmail.com",
                Date(),
                Gender.FEMININO,
                "Brasil"
            )
        )


        //save plantypes
        sinistroRepository.save(
            Sinistro(
                UUID.randomUUID(), Date(), StepCSJ.ACORDO, Date(),
                2000.0, 20.0, ResolutionType.CHARGEBACK_PAGO,
                Date(), SinistroType.FURTO_QUALIFICADO,
                true, true,
                true, Date(), 200.0f,
                2222.0, 20.0, false
            )
        )

        misappropriationRepository.save(
            Misappropriation(
                UUID.randomUUID(),
                Date(),
                StepCSJ.EXTRAJUDICIAL,
                Date(),
                5000.0,
                4500.0,
                4000.0,
                ResolutionType.RECORRENCIA_PAGA,
                "PAC213213BR",
                PayMethod.NORMAL,
                false,
                Date()
            )
        )
        misappropriationRepository.save(
            Misappropriation(
                UUID.randomUUID(),
                Date(),
                StepCSJ.EXTRAJUDICIAL,
                Date(),
                10000.0,
                3500.0,
                2000.0,
                ResolutionType.CHARGEBACK_PAGO,
                "PAC4313213BR",
                PayMethod.RECORRENCIA,
                false,
                Date()
            )
        )

        misappropriationRepository.save(
            Misappropriation(
                UUID.randomUUID(),
                Date(),
                StepCSJ.ACORDO,
                Date(),
                10000.0,
                3500.0,
                2000.0,
                ResolutionType.COMPRA_MAIS_MULTA,
                "PAC897450813-BR",
                PayMethod.RECORRENCIA,
                false,
                Date()
            )
        )
        technicalSupportRepository.save(
            TechnicalSupport(
                UUID.randomUUID(), Date(), StepCSJ.ACORDO, Date(), 12000.0, 10000.0,
                90000.0, ResolutionType.COMPRA,
                "2223201BR", 25000.0, true
            )
        )

        //save users
        userRepository.save(
            UserApp(
                null,
                "Paulera",
                "paulo@gmail.com",
                "paulo",
                bCryptPasswordEncoder.encode("senha123456"),
                SystemUserRoles.ROLE_ANALYST
            )
        )
        userRepository.save(
            UserApp(
                null,
                "Guilherme",
                "gui@gmail.com",
                "gui",
                bCryptPasswordEncoder.encode("senha123456"),
                SystemUserRoles.ROLE_ADMIN
            )
        )

        //save plan
        planRepository.save(
            Plan(
                UUID.randomUUID(),
                true,
                "xasd212",
                47000.0,
                userRepository.findAll()[0],
                Date(),
                Date(),
                listOf(
                    productRepository.findAll()[0],
                    productRepository.findAll()[1]
                ),
                customerRepository.findAll()[0],
                bondsmanRepository.findAll()[0],
                listOf(
                    misappropriationRepository.findAll()[2],
                    sinistroRepository.findAll()[0],
                )
            )
        )
        planRepository.save(
            Plan(
                UUID.randomUUID(),
                true,
                "rsd201",
                7000.0,
                userRepository.findAll()[0],
                Date(),
                Date(),
                listOf(
                    productRepository.findAll()[2],
                    productRepository.findAll()[1]
                ),
                customerRepository.findAll()[1],
                bondsmanRepository.findAll()[1],
                listOf(misappropriationRepository.findAll()[0])
            )
        )
        planRepository.save(
            Plan(
                UUID.randomUUID(),
                true,
                "k2310",
                9400.0,
                userRepository.findAll()[0],
                Date(),
                Date(),
                listOf(
                    productRepository.findAll()[0],
                    productRepository.findAll()[1],
                    productRepository.findAll()[2]
                ),
                customerRepository.findAll()[2],
                bondsmanRepository.findAll()[2],
                listOf(misappropriationRepository.findAll()[1])
            )
        )
        planRepository.save(
            Plan(
                UUID.randomUUID(),
                true,
                "puy123",
                3000.0,
                userRepository.findAll()[0],
                Date(),
                null,
                listOf(
                    productRepository.findAll()[2]
                ),
                customerRepository.findAll()[3],
                bondsmanRepository.findAll()[3],
                listOf(technicalSupportRepository.findAll()[0])
            )
        )

        //save task
        val task = Tasks(
            null,
            "Confirmar pagamento",
            planRepository.findAll()[0],
            Date(),
            Date(),
            mutableMapOf(1 to "Ligar dia 10/02", 2 to "Falar com CEO sobre esse caso",3 to  "Vai pagar dia 25/10")
        )
        val task2 = Tasks(
            null,
            "ligar para fulano",
            planRepository.findAll()[1],
            Date(),
            Date(),
            mutableMapOf(1 to "Plantar arvores", 2 to "Falar com vizinho sobre gastar menos agua",3 to  "Ser feliz")
        )

        tasksRepository.save(task)
        tasksRepository.save(task2)

        //save scheduler
        scheduleRepository.save(
            Scheduler(
                null,
                userRepository.findAll()[0],
                mutableListOf(tasksRepository.findAll()[0], tasksRepository.findAll()[1]),
            )
        )

    }
}

