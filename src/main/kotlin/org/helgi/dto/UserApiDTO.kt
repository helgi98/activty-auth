package org.helgi.dto

data class RegisterForm(val username: String, val password: String)

data class UserData(val username: String, val password: String, val permissions: Set<String>)