package com.promineo.jeep.entity;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Option {
  private Long OptionPK;
  private String OptionId ;
  private OptionType category;
  private String manufacturer;
  private String name;
  private BigDecimal price;

}
