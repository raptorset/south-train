package com.automation.globan.booking.utils.dates;

import java.util.HashMap;

import org.fluttercode.datafactory.impl.DataFactory;
import org.testng.annotations.DataProvider;

public class DataProviderSource {
	@DataProvider(name = "ferrero")
	public static Object[][] hashdata() {
		DataFactory dataFactory = new DataFactory();
		HashMap<String, String> hash_map = new HashMap<String, String>();
		hash_map.put("city", "Bogota");
		hash_map.put("childAge", "9 años");
		hash_map.put("numberRooms", "1");
		hash_map.put("firstName", dataFactory.getFirstName());
		hash_map.put("lastName", dataFactory.getLastName());
		hash_map.put("email", dataFactory.getEmailAddress());
		hash_map.put("optionCountry", "Colombia");
		hash_map.put("number", "3108124682");
		hash_map.put("titularName", dataFactory.getName());
		hash_map.put("cardType", "MasterCard");
		hash_map.put("cardNumber", "8854 4744 4441 4477 ");
		hash_map.put("CaducidadDate", "01-ene");
		hash_map.put("caducidadYear", "2029");
		hash_map.put("code", "8855");
		return new Object[][] { { hash_map } };
	}

}
