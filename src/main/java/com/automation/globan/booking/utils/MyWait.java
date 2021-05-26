package com.automation.globan.booking.utils;

import java.util.Calendar;
import java.util.Date;

public class MyWait {

	public static void inMilliseconds(int milliseconds) {
		Calendar calendar = Calendar.getInstance();
		Date hora = new Date();
		calendar.setTime(hora);
		if (milliseconds >= 1000) {
			calendar.add(Calendar.SECOND, (milliseconds / 1000));
		} else if (milliseconds < 1000) {
			calendar.add(Calendar.MILLISECOND, (milliseconds));
		}
		while (hora.compareTo(calendar.getTime()) < 0) {
			hora = new Date();

		}
	}

}
