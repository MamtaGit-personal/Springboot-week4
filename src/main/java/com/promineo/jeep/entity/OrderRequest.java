package com.promineo.jeep.entity;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class OrderRequest {
    private String customer; 
    private JeepModel model; 
    private String trim; 
    private int doors;
    private String color; 
    private String engine; 
    private String tire; 
    private List< String> options;
   
  /*
   * private String customer; private JeepModel model; private String trim; private int doors;
   */
    
  /*
   * private List<@NotNull @Length(max=30)
   * 
   * @Pattern(regexp = "[\\w\\s]*") String> options;
   */
  
  //@NotNull
  //@Length(max=30)
  // @Pattern(regexp = "[\\w\\s]*") // each name must be all upper case or _ and as many (*)

  //@Positive
  //@Min(2)
  //@Max(4)
 }
