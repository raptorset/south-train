package com.automation.globan.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WriteYourData extends BasePage {

	private static final String MESSAGE = "No es necesario pagar hoy.";

	public WriteYourData(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div[class='bui-group bui-spacer--large'] strong")
	private WebElement successMessages;

	@FindBy(id = "bp_travel_purpose_leasure")
	private WebElement viajasPorTrabajoCheckbox;
	@FindBy(id = "firstname")
	private WebElement inputFirstName;
	@FindBy(id = "lastname")
	private WebElement inputLastName;
	@FindBy(id = "email")
	private WebElement inputEmail;
	@FindBy(id = "email_confirm")
	private WebElement inputConfirmEmail;
	@FindBy(id = "notstayer_false")
	private WebElement iAmHuespedcheckBox;
	@FindBy(name = "book")
	private WebElement ultimosPasosButton;
	@FindBy(id = "mailinglist")
	private WebElement accessOfertCheckBox;

	public LastStepsPage enterDetails(String firstName, String lastName, String email, String confirmEmail) {
		clickButton(viajasPorTrabajoCheckbox);
		setElementText(inputFirstName, firstName);
		setElementText(inputLastName, lastName);
		setElementText(inputEmail, email);
		setElementText(inputConfirmEmail, confirmEmail);
		clickButton(iAmHuespedcheckBox);
		clickButton(ultimosPasosButton);
		clickButton(accessOfertCheckBox);
		myWaitMilliseconts(5000);
		return new LastStepsPage(getDriver());

	}

	public boolean messageValidation() {
		if (successMessages.getText().equals(MESSAGE)) {
			return true;

		} else {
			return false;
		}

	}

}
