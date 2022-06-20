package org.helgi.util

import org.apache.commons.codec.digest.DigestUtils

fun hashPassword(password: String): String = DigestUtils.sha256Hex(password)

fun checkPassword(password: String, passwordHash: String): Boolean = hashPassword(password) == passwordHash