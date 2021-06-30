package com.promineo.jeep.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.promineo.jeep.entity.Jeep;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {
  
  @Override
  public List<Jeep> fetchJeeps(String model, String trim) {
    log.info("The fetch Jeep method was called with model={}, trim={}", model, trim); // Doesn't work in STS
    return null;
  }

}
