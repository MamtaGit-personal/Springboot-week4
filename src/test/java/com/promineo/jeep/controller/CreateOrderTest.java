package com.promineo.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
      String body = createOrderBody();
      String uri = getBaseUriForOrders();
      
      TestRestTemplate restTemplate = new TestRestTemplate();
        
      HttpHeaders headers = new HttpHeaders(); 
      headers.setContentType(MediaType.APPLICATION_JSON);
       
      HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
      
      System.out.println("My header is: " + headers); 
      System.out.println("The uri is: " + uri);
      System.out.println("My bodyEntity is: " + bodyEntity);
      
      // When: the order is sent
      ResponseEntity<Order> response = restTemplate.exchange(uri, HttpMethod.POST, bodyEntity,
          Order.class);
      
      /*
       * ResponseEntity<Order> response = getRestTemplate().exchange(uri, HttpMethod.POST,
       * bodyEntity, Order.class);
       */
      System.out.println("My Response is: " + response);
      System.out.println("My Response body is: " + response.getBody());
      System.out.println("My Response header is: " + response.getHeaders());
      
      // Then: a 201 status is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            
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
