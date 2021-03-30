/**
 * NOTE: This class is auto generated by the io.swagger code generator program (2.4.18).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.model.MessagingConsumerConfig;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.io.swagger.codegen.languages.SpringCodegen", date = "2021-03-30T12:50:03.016+02:00")

@Api(value = "consume", description = "the consume API")
@RequestMapping(value = "/v1/messaging")
public interface ConsumeApi {

    @ApiOperation(value = "consume messages", nickname = "consume", notes = "", tags={ "consumer", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Ok") })
    @RequestMapping(value = "/consume",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> consume(@ApiParam(value = "", required = true) @Valid @RequestBody MessagingConsumerConfig consumerConfig);

}
