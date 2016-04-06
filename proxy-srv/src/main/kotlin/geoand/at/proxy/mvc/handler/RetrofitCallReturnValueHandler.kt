package geoand.at.proxy.mvc.handler

import geoand.at.proxy.mvc.util.RetrofitCallToDeferredResult
import org.springframework.core.MethodParameter
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.async.WebAsyncUtils
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler
import org.springframework.web.method.support.ModelAndViewContainer
import retrofit2.Call

/**
 * Created by gandrianakis on 6/4/2016.
 */
class RetrofitCallReturnValueHandler : AsyncHandlerMethodReturnValueHandler {

    val retrofitCallToDeferredResult = RetrofitCallToDeferredResult()

    override fun isAsyncReturnValue(returnValue: Any?, returnType: MethodParameter?): Boolean {
        return returnValue != null && returnValue is Call<*>

    }

    override fun supportsReturnType(returnType: MethodParameter?): Boolean {
        return Call::class.java.isAssignableFrom(returnType?.parameterType)
    }

    override fun handleReturnValue(returnValue: Any?, returnType: MethodParameter?, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest?) {
        if (returnValue == null) {
            mavContainer?.isRequestHandled = true
            return
        }

        if(returnValue is Call<*>) { //will always be true in the context of Spring call
            WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(retrofitCallToDeferredResult.convert(returnValue), mavContainer)
        }
        else {
            mavContainer?.isRequestHandled = true
            return
        }
    }
}