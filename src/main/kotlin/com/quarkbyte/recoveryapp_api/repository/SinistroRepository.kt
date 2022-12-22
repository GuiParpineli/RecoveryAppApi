package com.quarkbyte.recoveryapp_api.repository

import com.quarkbyte.recoveryapp_api.model.cases.Sinistro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SinistroRepository : JpaRepository<Sinistro, UUID>
