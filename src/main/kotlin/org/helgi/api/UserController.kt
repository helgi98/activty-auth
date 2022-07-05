package org.helgi.api

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import org.helgi.dto.RegisterForm
import org.helgi.dto.TokenCheck
import org.helgi.dto.UserData
import org.helgi.service.UserService

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("user")
class UserController(val userService: UserService) {
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post("register")
    fun register(registerForm: RegisterForm): UserData {
        return userService.register(registerForm)
    }

    @Get("data")
    fun getUserData(authentication: Authentication): UserData {
        return userService.getUserData(authentication)
    }
}