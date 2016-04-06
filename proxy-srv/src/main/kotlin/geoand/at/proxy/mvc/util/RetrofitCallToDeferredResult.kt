package geoand.at.proxy.mvc.util

import geoand.at.proxy.mvc.exception.EmptyResultsException
import org.springframework.web.context.request.async.DeferredResult
import retrofit2.Call
import retrofit2.Response

/**
 * Created by gandrianakis on 6/4/2016.
 */
class RetrofitCallToDeferredResult constructor(private val timeoutMillis: Long = 20000) {

    fun <T> convert(retrofitCall: Call<T>) : DeferredResult<T> {
        val deferred = DeferredResult<T>(timeoutMillis)
        retrofitCall.enqueue(object: retrofit2.Callback<T> {
            override fun onFailure(call: Call<T>?, t: Throwable?) {
                deferred.setErrorResult(t)
            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                val body = response?.body()
                if(isEmptyResult(body)) {
                    deferred.setErrorResult(EmptyResultsException())
                }
                deferred.setResult(response?.body())
            }

        })
        return deferred
    }

    private fun <T> isEmptyResult(body: T?): Boolean {
        if(null == body) {
            return true
        }

        if(body is List<*>) {
            return body.isEmpty()
        }

        return false
    }
}