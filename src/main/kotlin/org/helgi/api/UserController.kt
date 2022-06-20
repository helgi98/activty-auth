package org.helgi.api

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.helgi.dto.RegisterForm
import org.helgi.dto.UserData
import org.helgi.service.UserService


@Controller("user")
class UserController(val userService: UserService) {
    @Post
    fun register(registerForm: RegisterForm): UserData {
        return userService.register(registerForm)
    }
}