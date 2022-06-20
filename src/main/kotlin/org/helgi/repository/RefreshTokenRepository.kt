package org.helgi.repository

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import org.helgi.entity.RefreshToken
import java.util.*
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, Long> {

    @Transactional
    fun save(
        @NotBlank username: String,
        @NotBlank refreshToken: String,
        revoked: Boolean
    ): RefreshToken

    fun findByRefreshToken(@NotBlank refreshToken: String): Optional<RefreshToken>

    @Transactional
    fun updateByUsername(
        @NotBlank username: String,
        revoked: Boolean
    )
}