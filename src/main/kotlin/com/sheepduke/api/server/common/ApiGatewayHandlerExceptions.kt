package com.sheepduke.api.server.common

class BadRequestException(
    override val message: String = "")
    : RuntimeException(message)
