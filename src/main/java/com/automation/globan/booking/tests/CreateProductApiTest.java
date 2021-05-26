package com.automation.globan.booking.tests;

import org.testng.annotations.BeforeTest;

public class CreateProductApiTest extends BaseTest {

	private static int id;

	@BeforeTest
	public void createProducApiTest() {
		id = baseWeb.requestPostWithAuthorization();

	}

}
