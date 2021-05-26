package com.automation.globan.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.globan.booking.utils.DatePicker;

public class DatePickerPage extends BasePage {
	
	
	
	

	public DatePickerPage(WebDriver driver) {
		super(driver);
		this.datePicker = new DatePicker(driver);
	}
	
	
	

	@FindBy(id = "datepicker")
	private WebElement dateField;

	@FindBy(id = "div[class='bui-calendar__content']")
	private DatePicker datePicker;

	public DatePicker getDatePicker() {
		dateField.click();
		
		return datePicker;
	}

	public String getSelectedDate() {
		return this.dateField.getAttribute("value");
	}

}
