package org.helgi.service

import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.helgi.dto.RegisterForm
import org.helgi.dto.UserData
import org.helgi.entity.Permission
import org.helgi.exception.ConflictException
import org.helgi.repository.UserRepository
import org.helgi.util.hashPassword

@Singleton
class UserService(private val userRepository: UserRepository) {
    fun register(registerForm: RegisterForm): UserData {
        userRepository.findByUsername(registerForm.username).ifPresent {
            throw ConflictException("Username already registered")
        }

        return userRepository.save(registerForm.username, hashPassword(registerForm.password)).run {
            UserData(username, getPermissions().map { it.name }.toSet())
        }
    }

    fun getUserData(authentication: Authentication): UserData {
        return userRepository.findByUsername(authentication.name).map {
            it.run {
                UserData(username, getPermissions().map(Permission::name).toSet())
            }
        }.get()
    }
}