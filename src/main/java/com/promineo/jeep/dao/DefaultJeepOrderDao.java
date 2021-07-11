package com.promineo.jeep.dao;

import org.springframework.stereotype.Component;
import com.promineo.jeep.entity.Order;
import com.promineo.jeep.entity.OrderRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DefaultJeepOrderDao implements JeepOrderDao {

  @Override
  public Order createOrder(OrderRequest orderRequest) {
    log.debug("DAO layer is working FINE.....");
    return null;
  }

}
