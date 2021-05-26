package com.automation.globan.booking.utils;

import static com.automation.globan.booking.utils.webservices.ConstansWebServices.AUTHORIZATION;
import static com.automation.globan.booking.utils.webservices.ConstansWebServices.BASIC;
import static com.automation.globan.booking.utils.webservices.ConstansWebServices.BODY;
import static com.automation.globan.booking.utils.webservices.ConstansWebServices.ID;
import static com.automation.globan.booking.utils.webservices.ConstansWebServices.TOKEN;
import static io.restassured.http.ContentType.JSON;

import com.automation.globan.booking.utils.webservices.ConstansWebServices;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseWebServises {

	RequestSpecification request = RestAssured.with();
	public Response response;

	public int requestPostWithAuthorization() {

		return request.given().header(AUTHORIZATION, BASIC + TOKEN).contentType(JSON).body(BODY).when()
				.post(ConstansWebServices.BASE_POST).then().extract().path(ID);

	}

}
