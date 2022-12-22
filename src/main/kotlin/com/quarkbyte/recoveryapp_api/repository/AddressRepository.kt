package com.quarkbyte.recoveryapp_api.repository

import com.quarkbyte.recoveryapp_api.model.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AddressRepository : JpaRepository<Address, UUID>
