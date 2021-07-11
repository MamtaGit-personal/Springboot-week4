package com.promineo.jeep.dao;

import com.promineo.jeep.entity.Order;
import com.promineo.jeep.entity.OrderRequest;

public interface JeepOrderDao {

  Order createOrder(OrderRequest orderRequest);

}
