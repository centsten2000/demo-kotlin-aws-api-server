package com.sheepduke.api.server.common

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import mu.KotlinLogging

object ApiGatewayRequestKeys {
    const val BODY: String = "body"
}

object ApiGatewayResponseKeys {
    const val STATUS_CODE = "statusCode"
    const val BODY: String = "body"
}

/**
 * A thin wrapper of [RequestHandler].
 */
abstract class ApiGatewayServer<Out>
    : RequestHandler<Map<String, Any>, Map<String, Any>> {
    private val log = KotlinLogging.logger {}

    override fun handleRequest(input: Map<String, Any>, context: Context): Map<String, Any> {
        log.info { "Request: $input" }
        log.info {
            """
            Environment:
            PATH=${Globals.Environment.PATH}
        """.trimIndent()
        }

        val response: ApiGatewayResponse<Out> = try {
            val requestBody = input[ApiGatewayRequestKeys.BODY] as String?
            val request = ApiGatewayRequest(body = requestBody)
            handleRequest(request)
        } catch (e: BadRequestException) {
            log.warn { "Bad request: ${e.message}" }
            ApiGatewayResponse(statusCode = 400)
        }

        return response.toMap()
    }

    abstract fun handleRequest(request: ApiGatewayRequest): ApiGatewayResponse<Out>
}

/**
 * Convert [ApiGatewayResponse] to corresponding [Map] that will be returned to the caller.
 */
fun <T> ApiGatewayResponse<T>.toMap(): Map<String, Any> {
    return mapOf(
        ApiGatewayResponseKeys.STATUS_CODE to this.statusCode,
        ApiGatewayResponseKeys.BODY to this.bodyString)
}