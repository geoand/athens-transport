package geoand.at.proxy.route

import geoand.at.raw.route.Route
import geoand.at.raw.route.RouteService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import retrofit2.Call

/**
 * Created by gandrianakis on 12/4/2016.
 */
@RestController
@RequestMapping("/route")
@Api (value="/route", description="Route operations")
class RouteController @Autowired constructor(private val routeService: RouteService){

    @ApiOperation (value="Fetches a list of routes passing by a stop", nickname="routeByStopCode", response = Route::class, responseContainer = "List")
    @RequestMapping(value = "stop/{stopCode}", method = arrayOf(RequestMethod.GET))
    fun byStopCode(@PathVariable("stopCode") stopCode: String): Call<List<Route>?> = routeService.byStopCode(stopCode)

    @ApiOperation (value="Fetches a list of routes connected to a line", nickname="routeByLineCode", response = Route::class, responseContainer = "List")
    @RequestMapping(value = "line/{lineCode}", method = arrayOf(RequestMethod.GET))
    fun byLineCode(@PathVariable("lineCode") lineCode: String): Call<List<Route>?> = routeService.byLineCode(lineCode)
}