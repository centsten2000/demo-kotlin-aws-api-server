package com.sheepduke.api.server.hello

import com.sheepduke.api.server.common.ApiGatewayRequest
import com.sheepduke.api.server.common.ApiGatewayResponse
import com.sheepduke.api.server.common.ApiGatewayServer
import com.sheepduke.api.server.common.BadRequestException

data class HelloRequest(
    var name: String = ""
)

data class HelloResult(
    var message: String = ""
)

/**
 * Handlers /hello request.
 */
class HelloHandler : ApiGatewayServer<HelloResult>() {

    override fun handleRequest(request: ApiGatewayRequest): ApiGatewayResponse<HelloResult> {
        val helloRequest = request.decodeBody<HelloRequest>() ?: throw BadRequestException()
        return ApiGatewayResponse(body = HelloResult(message = "Hello, ${helloRequest.name}"))
    }
}