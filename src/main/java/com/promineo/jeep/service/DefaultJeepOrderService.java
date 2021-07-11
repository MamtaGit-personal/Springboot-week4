package com.promineo.jeep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineo.jeep.dao.JeepOrderDao;
import com.promineo.jeep.entity.Order;
import com.promineo.jeep.entity.OrderRequest;

@Service
public class DefaultJeepOrderService implements JeepOrderService {
  
  @Autowired
  private JeepOrderDao jeepOrderDao;
  
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    // TODO Auto-generated method stub
    return jeepOrderDao.createOrder(orderRequest);
  }

}
