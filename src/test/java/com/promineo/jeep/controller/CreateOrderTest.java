package com.promineo.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineo.jeep.controller.support.CreateOrderTestSupport;
import com.promineo.jeep.entity.Order;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, config = @SqlConfig(encoding = "utf-8"))
class CreateOrderTest extends CreateOrderTestSupport {
 
  @Test
  void testCreateOrderReturnsSuccess201() {
    
      // Given: an order as JSON
      
      //It's in com.promineo.jeep.controller.support package-> CreateOrderTestSupport.java class
      String body = createOrderBody(); 
      //TestRestTemplate restTemplate = new TestRestTemplate();
      
      HttpHeaders headers = new HttpHeaders(); 
      headers.setContentType(MediaType.APPLICATION_JSON);
      
      //It's in com.promineo.jeep.controller.support package-> BaseTest.java class
      String uri = getBaseUriForOrders(); 
      
     HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
      
      System.out.println("My header is: " + headers + "uri is: " + uri);
      System.out.println("My bodyEntity is: " + bodyEntity); //bodyEntity variable is working fine
      
      // When: the order is sent
      ResponseEntity<Order> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
           Order.class);
      
      System.out.println("My Response Status Code code is: " + response.getStatusCodeValue()); //returning 201
      System.out.println("My Response body is: " + response.getBody()); // Getting null here
      System.out.println("My Response header is: " + response.getHeaders()); //working fine
      
      // Then: a 201 status is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED); // getting 201 success
            
      // And: the returned order is correct
      assertThat(response.getBody()).isNotNull();
      System.out.println("assertThat response.getBody() is  NOT null");
     
      //Order order = response.getBody();
     
      //System.out.println("My order is: " + order);
      
     /*
       * Order order = response.getBody();
       * 
       * assertThat(order.getCustomer().getCustomerId()).isEqualTo("MORISON_LINA");
       * assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.WRANGLER);
       * assertThat(order.getModel().getTrimLevel()).isEqualTo("Sport Altitude");
       * assertThat(order.getModel().getNumDoors()).isEqualTo(4);
       * assertThat(order.getColor().getColorId()).isEqualTo("EXT_NACHO");
       * assertThat(order.getEngine().getEngineId()).isEqualTo("2_0_TURBO");
       * assertThat(order.getTire().getTireId()).isEqualTo("35_TOYO");
       * assertThat(order.getOptions()).hasSize(6);
       */


  }

}
