package org.helgi.service

import jakarta.inject.Singleton
import org.helgi.dto.RegisterForm
import org.helgi.dto.UserData
import org.helgi.repository.UserRepository
import org.helgi.util.hashPassword

@Singleton
class UserService(private val userRepository: UserRepository) {
    fun register(registerForm: RegisterForm): UserData {
        userRepository.findByUsername(registerForm.username).ifPresent {
            // Add correct exception
            throw Exception("Username already registered")
        }

        return userRepository.save(registerForm.username, hashPassword(registerForm.password)).run {
            UserData(username, password, permissions.map { it.name }.toSet())
        }
    }
}