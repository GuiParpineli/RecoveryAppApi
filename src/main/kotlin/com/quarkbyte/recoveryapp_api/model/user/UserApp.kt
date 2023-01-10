package com.quarkbyte.recoveryapp_api.model.user

import com.quarkbyte.recoveryapp_api.model.enums.SystemUserRoles
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import lombok.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
class UserApp(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val name: @NotEmpty String = "",
    val lastname: String = "",
    val jwt: String? = null,
    val email: String = "",
    private var username: String = "",
    private var password: @NotEmpty String = "",

    @Enumerated(EnumType.STRING)
    var systemUserRoles: SystemUserRoles = SystemUserRoles.ROLE_USER

) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        val grantedAuthority = SimpleGrantedAuthority(systemUserRoles.name)
        return Collections.singleton(grantedAuthority)
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}
