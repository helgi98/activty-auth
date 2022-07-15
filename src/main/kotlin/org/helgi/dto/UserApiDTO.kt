package org.helgi.dto

import javax.validation.constraints.NotBlank

data class RegisterForm(@NotBlank val username: String, @NotBlank val password: String)

data class UserData(val username: String, val permissions: Set<String>)