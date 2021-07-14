package com.promineo.jeep.entity;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import lombok.Builder;
import lombok.Data;
/* customer = customer_id from the customers table
 * (model, trim, doors) = (model_id, trim_level, num_doors respectively from the models table
 * color = color_id from the colors table
 * tire = tire_id from the tires table
 * engine = engine_id from the engines table * 
 */

@Builder
@Data
public class OrderRequest {
    @NotNull
    @Length(max=30)
    @Pattern(regexp = "[\\w\\s]*") // each name must be all upper case or _ and as many (*)
    private String customer; 
    
    @NotNull
    private JeepModel model; 
    
    @NotNull
    @Length(max=30)
    @Pattern(regexp = "[\\w\\s]*") 
    private String trim; 
    
    @Positive
    @Min(2)
    @Max(4)
    private int doors;
    
    @NotNull
    @Length(max=30)
    @Pattern(regexp = "[\\w\\s]*") 
    private String color; 
    
    @NotNull
    @Length(max=30)
    @Pattern(regexp = "[\\w\\s]*") 
    private String engine; 
    
    @NotNull
    @Length(max=30)
    @Pattern(regexp = "[\\w\\s]*") 
    private String tire; 
    
    private List<@NotNull @Length(max=30) @Pattern(regexp = "[\\w\\s]*") String> options;
    //private List<String> options;
         
 }
