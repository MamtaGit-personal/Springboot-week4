package com.promineo.jeep.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderRequest {
    @NotNull
    //@Length(max=30)
    //@Pattern(regexp = "[\\w\\s]*") // each name must be all upper case or _ and as many (*)
    private String customer; 
    
    @NotNull
    private JeepModel model; 
    
    //@NotNull
    //@Length(max=30)
    //@Pattern(regexp = "[\\w\\s]*") 
    private String trim; 
    
    //@Positive
    //@Min(2)
    //@Max(4)
    private int doors;
    
    //@NotNull
    //@Length(max=30)
    //@Pattern(regexp = "[\\w\\s]*") 
    private String color; 
    
    //@NotNull
    //@Length(max=30)
   // @Pattern(regexp = "[\\w\\s]*") 
    private String engine; 
    
    //@NotNull
    //@Length(max=30)
    //@Pattern(regexp = "[\\w\\s]*") 
    private String tire; 
    
    private List<String> options;
    //private List<@NotNull @Length(max=30) @Pattern(regexp = "[\\w\\s]*") String> options;
   
  /*
   * private String customer; private JeepModel model; private String trim; private int doors;
   */
      
 }
