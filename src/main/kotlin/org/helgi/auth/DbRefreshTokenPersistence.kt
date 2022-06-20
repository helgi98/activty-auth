package org.helgi.auth

import io.micronaut.security.authentication.Authentication
import io.micronaut.security.errors.IssuingAnAccessTokenErrorCode.INVALID_GRANT
import io.micronaut.security.errors.OauthErrorResponseException
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent
import io.micronaut.security.token.refresh.RefreshTokenPersistence
import org.helgi.repository.RefreshTokenRepository
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

class DbRefreshTokenPersistence(private val repo: RefreshTokenRepository) : RefreshTokenPersistence {
    override fun persistToken(event: RefreshTokenGeneratedEvent) {
        if (event.refreshToken != null && event.authentication?.name != null) {
            val payload = event.refreshToken
            repo.save(event.authentication.name, payload, false)
        }
    }

    override fun getAuthentication(refreshToken: String): Publisher<Authentication> {
        return Flux.create { emitter: FluxSink<Authentication> ->
            repo.findByRefreshToken(refreshToken).ifPresentOrElse({
                it.apply {
                    if (revoked) {
                        emitter.error(OauthErrorResponseException(INVALID_GRANT, "refresh token revoked", null))
                    } else {
                        emitter.next(Authentication.build(username))
                        emitter.complete()
                    }
                }
            }, {
                emitter.error(OauthErrorResponseException(INVALID_GRANT, "refresh token not found", null))
            })
        }
    }
}