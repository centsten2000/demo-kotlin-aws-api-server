package com.sheepduke.api.server.common

import com.fasterxml.jackson.core.JsonProcessingException

data class ApiGatewayRequest(
    /**
     * The request body as String.
     */
    var body: String? = null) {

    /**
     * Decode request body as JSON string.
     */
    inline fun <reified T> decodeBody(): T? {
        return try {
            if (body == null) {
                null
            } else {
                Globals.objectMapper.readValue(body, T::class.java)
            }
        } catch (e: JsonProcessingException) {
            null
        }
    }
}