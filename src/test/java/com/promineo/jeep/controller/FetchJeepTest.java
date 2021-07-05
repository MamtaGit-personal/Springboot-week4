package com.promineo.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doThrow;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
//import org.springframework.test.jdbc.JdbcTestUtils;
import com.promineo.jeep.Constants;
import com.promineo.jeep.controller.support.FetchJeepTestSupport;
import com.promineo.jeep.entity.Jeep;
import com.promineo.jeep.entity.JeepModel;
import com.promineo.jeep.service.JeepSalesService;


class FetchJeepTest {
  
  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test")
  @Sql(scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
      "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
      config = @SqlConfig(encoding = "utf-8"))
  class TestsThatDoNotPolluteTheApplicationContext extends FetchJeepTestSupport {
    @Test
    void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
      // System.out.println(getBaseUri());

      // Given: a valid model, trim and URI
      JeepModel model = JeepModel.WRANGLER;
      String trim = "Sport";
      String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
      System.out.println(uri);

      // When: a connection is made to the URI
      ResponseEntity<List<Jeep>> response = 
          getRestTemplate().exchange(uri, HttpMethod.GET, null,
              new ParameterizedTypeReference<>() {});
       

      // Then: a success (Ok - 200) status code is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

      // And: the actual list returned is the same as the expected list
      List<Jeep> actual = response.getBody();
      List<Jeep> expected = buildExpected(); 
      
      //actual.forEach(jeep -> jeep.setModelPK(null));  //one way to return ModelPK as null. Not the best method.
      
      assertThat(actual).isEqualTo(expected);
     }
    
    //week3 - implemented on July 3rd, 2021 to test for bad input
    @Test
    void testThatAnErrorMeesageIsReturnedWhenAnUnknownTrimIsSupplied() {
      
      // Given: a valid model, trim and URI
      JeepModel model = JeepModel.WRANGLER;
      String trim = "An Invalid Trim";
      String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
      System.out.println(uri);

      // When: a connection is made to the URI
      /* Earlier, it was as written below
       * ResponseEntity<?> response = getRestTemplate().exchange(uri, HttpMethod.GET, null, new
       * ParameterizedTypeReference<>() {});
       */
      
      //Now it is as written below
      ResponseEntity<Map<String, Object>> response = 
          getRestTemplate().exchange(uri, HttpMethod.GET, null, 
              new ParameterizedTypeReference<>() {});
    
      // Then: a Not Found (404) status code is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

      //week3 - video 2
      // And: an error message is returned
      Map<String, Object> error = response.getBody();
      assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
     } 
    
  //week3 - video 3
    @ParameterizedTest
    @MethodSource("com.promineo.jeep.controller.FetchJeepTest#parametersForInvalidInput")
    void testThatAnErrorMeesageIsReturnedWhenAnInvalidValueIsSupplied(
        String model, String trim, String reason) {
      
      // Given: a valid model, trim and URI
       String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
       System.out.println(uri);

      // When: a connection is made to the URI
      ResponseEntity<Map<String, Object>> response = 
          getRestTemplate().exchange(uri, HttpMethod.GET, null, 
              new ParameterizedTypeReference<>() {});
    
      // Then: a Not Found (404) status code is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

      //week3 - video 2
      // And: an error message is returned
      Map<String, Object> error = response.getBody();
      assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
     }
  }
  
  static Stream<Arguments> parametersForInvalidInput() {
    return Stream.of(
      //@formatter:off
      arguments("WRANGLER", "@#$%^&&%", "Trim contains non-alpha-numeric value"),
      arguments("WRANGLER", "C".repeat(Constants.TRIM_MAX_LENGTH + 1), "Trim length too long"),
      arguments("INVALID", "Sport", "Model is NOT ENUM Value")
      //@formatter:off
      );
  }
  
  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test")
  @Sql(scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
      "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
      config = @SqlConfig(encoding = "utf-8"))
  class TestsThatPolluteTheApplicationContext extends FetchJeepTestSupport {
      @MockBean
      private JeepSalesService jeepSalesService;
      //week3 - video 4
      @Test
      void testThatAnUnplannedErrorResultsInA500Error() {
        
        // Given: a valid model, trim and URI
        JeepModel model = JeepModel.WRANGLER;
        String trim = "Sport";
        String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
        System.out.println(uri);

        doThrow(new RuntimeException("Ouch!")).when(jeepSalesService)
          .fetchJeeps(model, trim);
        
        // When: a connection is made to the URI
        ResponseEntity<Map<String, Object>> response = 
            getRestTemplate().exchange(uri, HttpMethod.GET, null, 
                new ParameterizedTypeReference<>() {});
      
        // Then: a Not Found (404) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        //week3 - video 4
        // And: an error message is returned
        Map<String, Object> error = response.getBody();
        assertErrorMessageValid(error, HttpStatus.INTERNAL_SERVER_ERROR);
       } 
    }
  
}
