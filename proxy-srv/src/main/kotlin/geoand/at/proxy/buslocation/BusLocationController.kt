package geoand.at.proxy.buslocation

import geoand.at.raw.buslocation.BusLocation
import geoand.at.raw.buslocation.BusLocationService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import retrofit2.Call

/**
 * Created by gandrianakis on 6/4/2016.
 */
@RestController
@RequestMapping("/bus/location")
@Api (value="/bus/location", description="Bus location operations")
class BusLocationController @Autowired constructor(private val busLocationService: BusLocationService){

    @ApiOperation (value="Fetches a list of bus locations by route code", nickname="busLocationsByRouteCode", response = BusLocation::class, responseContainer = "List")
    @RequestMapping(value = "route/{routeCode}", method = arrayOf(RequestMethod.GET))
    fun byRouteCode(@PathVariable("routeCode") routeCode: String): Call<List<BusLocation>?> = busLocationService.byRouteCode(routeCode)
}