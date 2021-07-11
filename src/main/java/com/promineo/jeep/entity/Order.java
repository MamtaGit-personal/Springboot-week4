package com.promineo.jeep.entity;

import java.util.List;
import lombok.Data;

@Data
public class Order {
    private Customer customer; 
    private Jeep model; 
    private Color color; 
    private Engine engine;
    private Tire tire; 
    private List<Option> options;
   
    /*
     * private String customer; private String model; private String trim; private int doors;
     */
      
}
