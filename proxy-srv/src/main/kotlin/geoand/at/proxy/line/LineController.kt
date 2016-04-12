package geoand.at.proxy.line

import geoand.at.raw.line.Line
import geoand.at.raw.line.LineService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by gandrianakis on 12/4/2016.
 */
@RestController
@RequestMapping("/line")
@Api (value="/line", description="Line operations")
class LineController @Autowired constructor(private val lineService: LineService) {

    @ApiOperation (value="Fetches all lines", nickname="all", response = Line::class, responseContainer = "List")
    @RequestMapping(value = "", method = arrayOf(RequestMethod.GET))
    fun all() = lineService.all()
}