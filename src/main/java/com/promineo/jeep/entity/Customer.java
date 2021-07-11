package com.promineo.jeep.entity;

import lombok.Data;

@Data
public class Customer {
  private Long customerPK;
  private String customerId;
  private String firstName; 
  private String lastName;
  private String phone;
}
