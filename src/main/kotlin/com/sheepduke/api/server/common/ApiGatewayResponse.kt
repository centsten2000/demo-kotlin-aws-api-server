package com.sheepduke.api.server.common

data class ApiGatewayResponse<T>(
    val statusCode: Int = 200,
    /**
     * Any body that can be serialized to String.
     */
    val body: T? = null) {

    val bodyString: String = Globals.objectMapper.writeValueAsString(body)
}