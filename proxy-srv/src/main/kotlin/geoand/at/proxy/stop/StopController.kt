package geoand.at.proxy.stop

import geoand.at.raw.stop.Stop
import geoand.at.raw.stop.StopService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import retrofit2.Call

/**
 * Created by gandrianakis on 6/4/2016.
 */
@RestController
@RequestMapping("/stop")
@Api (value="/stop", description="Stop operations")
class StopController @Autowired constructor(private val stopService: StopService){

    @ApiOperation (value="Fetches a list of the nearest stops", nickname="closest", response = Stop::class, responseContainer = "List")
    @RequestMapping(value = "closest", method = arrayOf(RequestMethod.GET))
    fun closest(@RequestParam("latitude") latitude: Double, @RequestParam("longitude") longitude: Double): Call<List<Stop>?> = stopService.closest(latitude, longitude)
}