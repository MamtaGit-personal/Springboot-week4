
package com.promineo.jeep.entity;

import org.hibernate.validator.constraints.Length;

public enum JeepModel {
 @Length(max=30)
  WRANGLER, GRAND_CHEROKEE, CHEROKEE, COMPASS, 
  RENEGADE, GLADIATOR, WRANGLER_4XE
}
