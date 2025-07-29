package com.billing.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
 public static String getTimeStamp()
 {
	 SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	 return sdf.format(new Date());
 }
}
