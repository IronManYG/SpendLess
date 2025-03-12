package dev.gaddal.core.data.networking

import dev.gaddal.core.domain.util.DataError
import dev.gaddal.core.domain.util.Result
import dev.gaddal.data.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException

/**
 * Makes a GET request with query parameters and returns the result.
 *
 * @param Response Expected response type.
 * @param route API endpoint route (relative to the base URL).
 * @param queryParameters Map of query parameters to include in the request.
 * @return [Result] containing the requested data or a network error.
 */
suspend inline fun <reified Response : Any> HttpClient.get(
    route: String,
    queryParameters: Map<String, Any?> = mapOf()
): Result<Response, DataError.Network> {
    return safeCall {
        get {
            url(constructRoute(route))
            // Add each query parameter to the request
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

/**
 * Makes a POST request with a request body and returns the result.
 *
 * @param Request Type of the request body.
 * @param Response Expected response type.
 * @param route API endpoint route (relative to the base URL).
 * @param body The request body to send.
 * @return [Result] containing the requested data or a network error.
 */
suspend inline fun <reified Request, reified Response : Any> HttpClient.post(
    route: String,
    body: Request
): Result<Response, DataError.Network> {
    return safeCall {
        post {
            url(constructRoute(route))
            setBody(body)
        }
    }
}

/**
 * Makes a PUT request with a request body and returns the result.
 *
 * @param Request Type of the request body.
 * @param Response Expected response type.
 * @param route API endpoint route (relative to the base URL).
 * @param body The request body to send.
 * @return [Result] containing the requested data or a network error.
 */
suspend inline fun <reified Request, reified Response : Any> HttpClient.put(
    route: String,
    body: Request
): Result<Response, DataError.Network> {
    return safeCall {
        put {
            url(constructRoute(route))
            setBody(body)
        }
    }
}

/**
 * Makes a DELETE request with query parameters and returns the result.
 *
 * @param Response Expected response type.
 * @param route API endpoint route (relative to the base URL).
 * @param queryParameters Map of query parameters to include in the request.
 * @return [Result] containing the requested data or a network error.
 */
suspend inline fun <reified Response : Any> HttpClient.delete(
    route: String,
    queryParameters: Map<String, Any?> = mapOf()
): Result<Response, DataError.Network> {
    return safeCall {
        delete {
            url(constructRoute(route))
            // Add each query parameter to the request
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

/**
 * Executes a safe network call and handles common network errors.
 *
 * @param T Expected response type.
 * @param execute A lambda function that executes an HTTP request and returns [HttpResponse].
 * @return [Result] containing the requested data or a network error.
 */
suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, DataError.Network> {
    val response = try {
        // Attempt to execute the HTTP request
        execute()
    } catch (e: UnresolvedAddressException) {
        // Handle network errors specifically
        e.printStackTrace()
        return Result.Error(DataError.Network.NO_INTERNET)
    } catch (e: SerializationException) {
        // Handle errors related to serialization of the response
        e.printStackTrace()
        return Result.Error(DataError.Network.SERIALIZATION)
    } catch (e: Exception) {
        // Handle any unknown errors, unless it's a cancellation
        if (e is CancellationException) throw e
        e.printStackTrace()
        return Result.Error(DataError.Network.UNKNOWN)
    }

    // Convert the HTTP response to a Result object
    return responseToResult(response)
}

/**
 * Maps the HTTP response status code to the corresponding result or error.
 *
 * @param T Expected response type.
 * @param response The HTTP response to process.
 * @return [Result] containing the requested data or a network error.
 */
suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.Network> {
    return when (response.status.value) {
        in 200..299 -> Result.Success(response.body<T>())
        401 -> Result.Error(DataError.Network.UNAUTHORIZED)
        408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
        409 -> Result.Error(DataError.Network.CONFLICT)
        413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
        429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
        else -> Result.Error(DataError.Network.UNKNOWN)
    }
}

/**
 * Constructs a full URL route based on the base URL in [BuildConfig].
 *
 * @param route The relative route or full URL.
 * @return The full URL for the API request.
 */
fun constructRoute(route: String): String {
    return when {
        // If the route already includes the base URL, return it as is
        route.contains(BuildConfig.BASE_URL) -> route
        // If the route starts with '/', append it directly to the base URL
        route.startsWith("/") -> BuildConfig.BASE_URL + route
        // Otherwise, add a '/' before the route and append it to the base URL
        else -> BuildConfig.BASE_URL + "/$route"
    }
}
