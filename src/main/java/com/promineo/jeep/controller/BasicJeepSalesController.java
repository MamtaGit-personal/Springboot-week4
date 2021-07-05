package com.promineo.jeep.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineo.jeep.entity.Jeep;
import com.promineo.jeep.entity.JeepModel;
import com.promineo.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicJeepSalesController implements JeepSalesController {
  
  @Autowired
  private JeepSalesService jeepSalesService;
  
  //@Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.debug("Controller layer: model={}, trim={}", model, trim);  // Does Not Work in STS
    return jeepSalesService.fetchJeeps(model, trim);
   }

}
