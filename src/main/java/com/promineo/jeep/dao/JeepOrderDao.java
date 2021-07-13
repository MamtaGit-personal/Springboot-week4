package com.promineo.jeep.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import com.promineo.jeep.entity.Color;
import com.promineo.jeep.entity.Customer;
import com.promineo.jeep.entity.Engine;
import com.promineo.jeep.entity.Jeep;
import com.promineo.jeep.entity.JeepModel;
import com.promineo.jeep.entity.Order;
import com.promineo.jeep.entity.OrderRequest;
import com.promineo.jeep.entity.Tire;
import com.promineo.jeep.entity.Option;

public interface JeepOrderDao {

  //Order createOrder(OrderRequest orderRequest);

  Optional<Customer> fetchCustomer(String customerID);

  Optional<Jeep> fetchModel(JeepModel model, String trim, int doors);

  Optional<Color> fetchColor(String colorID);

  Optional<Tire> fetchTire(String tireID);

  Optional<Engine> fetchEngine(String engineID);

  Order saveOrder(Customer customer, Jeep jeep, Color color, Tire tire, Engine engine,
      BigDecimal price, List<Option> options);

  List<Option> fetchOptions(List<String> optionIds);
  
}
