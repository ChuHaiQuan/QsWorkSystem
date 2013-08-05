package com.qs.gx.services.support;
/**
 * 
 * @author chuhaiquan
 *
 */

public enum weekMenu {
	 
      星期天((short) 1),
	  星期一((short) 2),
	  星期二((short) 3),
	  星期三((short) 4),
	  星期四((short) 5),
	  星期五((short) 6),
	  星期六((short) 7);
      private final Short status;
      
      private  weekMenu(Short status) {
  		this.status = status;
  	}
	  public Short getStatus() {
  		return status;
  	}

	  
	  
}
	