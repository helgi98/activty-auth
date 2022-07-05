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
@Table(name = "app_user")
data class User(
    @NotBlank
    var username: String,
    @NotBlank
    var password: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    private var permissions: Set<Permission>? = null
) {
    fun getPermissions() = permissions ?: emptySet()
}