package org.helgi.entity

import io.micronaut.data.annotation.DateCreated
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class RefreshToken(
    @Id
    @GeneratedValue
    val id: Long,
    @NotBlank
    val username: String,
    @NotBlank
    val refreshToken: String,
    val revoked: Boolean,
    @DateCreated
    val dateCreated: Instant
)