package com.promineo.jeep.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineo.jeep.entity.Order;
import com.promineo.jeep.entity.OrderRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/orders")  // To tell spring
@OpenAPIDefinition(info = @Info(title = "My First Jeep Order Service"), servers = {
    @Server(url = "http://localhost:8080", description = "My Local server.")})

public interface JeepOrderController {
  //public static final int TRIM_MAX_LENGTH = 30;

  //@formatter:off
  @Operation(
      summary = "Summary - Create an order for a Jeep",
      description = "Description - Returns the created Jeep",
      responses = {
          @ApiResponse(
              responseCode = "201", 
              description = "The created  Jeep is returned", 
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Order.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The Request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "A Jeep component was not found with input criteria",
                  content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))
          
      },
      parameters = {
          @Parameter(name = "orderRequest", 
              required = true, 
              description = "The order as JSON")
       }
)
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Order createOrder(@RequestBody OrderRequest orderRequest);
  //Order createOrder(@Valid @RequestBody OrderRequest orderRequest);
}
