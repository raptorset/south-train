package com.automation.globan.booking.utils;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyCustomWait {

	public final long SECONDS = 25;

	public WebDriverWait getWait(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, SECONDS);
		return wait;

	}

	public void waitElementVisible(WebDriver driver, WebElement element) {
		getWait(driver).until(visibilityOf(element));
	}

}
