package com.promineo.jeep.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineo.jeep.dao.JeepSalesDao;
import com.promineo.jeep.entity.Jeep;
import com.promineo.jeep.entity.JeepModel;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {
  
    // added for week 3 WenAPI
    @Autowired
    private JeepSalesDao jeepSalesDao;
    
    @Transactional(readOnly = true)  //added Week 3, video 2
    @Override
    public List<Jeep> fetchJeeps(JeepModel model, String trim) {
      log.info("Service layer: model={}, trim={}", model, trim); // Doesn't work in STS
      
      List<Jeep> jeeps = jeepSalesDao.fetchJeeps(model, trim);
      
      //Week3 - video 2 starts
      if(jeeps.isEmpty()) { String msg =
        String.format("No jeeps found with model=%s and trim=%s", model, trim);
        throw new NoSuchElementException(msg); 
        }
      //Week3 - video 2 ends
      
      Collections.sort(jeeps);
      return jeeps;
    
    }

 }
