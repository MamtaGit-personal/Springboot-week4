package com.promineo.jeep.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.promineo.jeep.entity.Jeep;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
public class BasicJeepSalesController implements JeepSalesController {

  public List<Jeep> fetchJeeps(String model, String trim) {
    return null;
  }

}
