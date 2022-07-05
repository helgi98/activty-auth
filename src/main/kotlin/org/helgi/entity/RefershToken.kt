package org.helgi.entity

import io.micronaut.data.annotation.DateCreated
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class RefreshToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @NotBlank
    val username: String,
    @NotBlank
    val refreshToken: String,
    val revoked: Boolean,
    @DateCreated
    var dateCreated: Instant? = null
)