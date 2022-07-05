package org.helgi.auth

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import jakarta.inject.Singleton
import org.helgi.repository.UserRepository
import org.helgi.util.checkPassword
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

@Singleton
class UserPasswordAuthenticationProvider(private val userRepository: UserRepository) : AuthenticationProvider {
    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>
    ): Publisher<AuthenticationResponse> {
        return Flux.create { emitter: FluxSink<AuthenticationResponse> ->
            userRepository.findByUsername(authenticationRequest.identity as String).map { user ->
                user!!.run {
                    if (checkPassword(authenticationRequest.secret as String, password))
                        AuthenticationResponse.success(username, getPermissions().map { it.name })
                    else AuthenticationResponse.failure("Credentials not matching")
                }
            }.ifPresentOrElse({
                emitter.next(it)
            }, {
                emitter.next(AuthenticationResponse.failure("User not found"))
            })
            emitter.complete()
        }
    }

}