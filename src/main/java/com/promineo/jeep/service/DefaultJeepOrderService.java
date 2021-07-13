package com.promineo.jeep.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineo.jeep.dao.JeepOrderDao;
import com.promineo.jeep.entity.Color;
import com.promineo.jeep.entity.Customer;
import com.promineo.jeep.entity.Engine;
import com.promineo.jeep.entity.Jeep;
import com.promineo.jeep.entity.Option;
import com.promineo.jeep.entity.Order;
import com.promineo.jeep.entity.OrderRequest;
import com.promineo.jeep.entity.Tire;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DefaultJeepOrderService implements JeepOrderService {
  
  @Autowired
  private JeepOrderDao jeepOrderDao;
  
  @Transactional
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    //Week 4 , video 2
    log.debug("Service layer, Order {}", orderRequest);
    
    Customer customer = getCustomer(orderRequest);
    
    Jeep jeep = getJeep(orderRequest);
    
    Color color = getColor(orderRequest);
    
    Tire tire = getTire(orderRequest);
    
    Engine engine = getEngine(orderRequest);
        
    //Get the list of Options now, week4, video 3
    List<Option> options = getOptions(orderRequest);
    
    // Add all the prices, week 4, video 3
    BigDecimal price = jeep.getBasePrice().add(color.getPrice())
        .add(engine.getPrice()).add(tire.getPrice());
    
    for(Option option: options) {
      price = price.add(option.getPrice());
    }
    log.debug("Service layer STARTS ---------------------------");
    log.debug("Customer ={} ", customer);
    log.debug("Jeep ={} ", jeep);
    log.debug("Color = {} ", color);
    log.debug("Tire= {} ", tire);
    log.debug("Engine = {}", engine);
    log.debug("Option = {}", options);
    log.debug("Service layer ENDS ---------------------------");
    // populate the order now week4, video 3
    return jeepOrderDao.saveOrder(customer, jeep, color, tire, engine, price, options);
  }

  private List<Option> getOptions(OrderRequest orderRequest) {
     return jeepOrderDao.fetchOptions(orderRequest.getOptions());
    
  }

  //////////////////////////////////////////////////////////////
  protected Engine getEngine(OrderRequest orderRequest) {
    return jeepOrderDao.fetchEngine(orderRequest.getEngine())
        .orElseThrow(() -> new NoSuchElementException("Engine with ID = " 
            + orderRequest.getEngine()+ " was NOT found"));
  }


  protected Tire getTire(OrderRequest orderRequest) {
    return jeepOrderDao.fetchTire(orderRequest.getTire())
        .orElseThrow(() -> new NoSuchElementException("Tire with ID = " 
            + orderRequest.getTire()+ " was NOT found"));
  }


  protected Color getColor(OrderRequest orderRequest) {
    return jeepOrderDao.fetchColor(orderRequest.getColor())
        .orElseThrow(() -> new NoSuchElementException("Color with ID = " 
            + orderRequest.getColor()+ " was NOT found"));
  }

  protected Jeep getJeep(OrderRequest orderRequest) {
    return jeepOrderDao.fetchModel(orderRequest.getModel(), orderRequest.getTrim(), orderRequest.getDoors())
    .orElseThrow(() -> new NoSuchElementException("Model with ID = " 
        + orderRequest.getModel()+ ", Trim = " +  orderRequest.getTrim() 
        + ", Doors = " + orderRequest.getDoors() + " was NOT found"));
   }

  protected Customer getCustomer(OrderRequest orderRequest) {
    return jeepOrderDao.fetchCustomer(orderRequest.getCustomer())
       .orElseThrow(() -> new NoSuchElementException("Customer with ID = " 
           + orderRequest.getCustomer()+ " was NOT found"));
  }

}
