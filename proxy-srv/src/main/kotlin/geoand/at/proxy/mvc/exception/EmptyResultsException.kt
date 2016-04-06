package geoand.at.proxy.mvc.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Created by gandrianakis on 6/4/2016.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Empty results")
class EmptyResultsException : RuntimeException()