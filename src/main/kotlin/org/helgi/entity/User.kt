package org.helgi.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Permission(
    @Id
    @GeneratedValue
    val id: Long,
    @NotBlank
    val name: String
)

@Entity
data class User(
    @Id
    @GeneratedValue
    val id: Long,
    @NotBlank
    val username: String,
    @NotBlank
    val password: String,
    @ManyToMany
    @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    val permissions: Set<Permission>
)