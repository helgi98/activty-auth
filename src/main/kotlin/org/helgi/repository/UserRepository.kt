package org.helgi.repository

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import org.helgi.entity.Permission
import org.helgi.entity.User
import java.util.*
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>

    @Transactional
    fun save(
        @NotBlank username: String,
        @NotBlank password: String,
        permissions: Set<Permission> = emptySet()
    ): User
}