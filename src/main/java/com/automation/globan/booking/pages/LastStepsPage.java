package com.automation.globan.booking.pages;

import static com.automation.globan.booking.utils.dates.ErrorMessage.CARD_ERROR;
import static com.automation.globan.booking.utils.dates.ErrorMessage.CODE_SEC_ERROR;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LastStepsPage extends BasePage {

	public LastStepsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "retain-leaving-users__modal")
	private WebElement casiLoTienesIframe;
	@FindBy(css = "select[name='cc1']")
	private WebElement regionSelect;
	@FindBy(id = "phone")
	private WebElement numberInput;
	@FindBy(id = "cc_name")
	private WebElement titularTarjetaInput;
	@FindBy(id = "cc_type")
	private WebElement cardTypeSelect;
	@FindBy(id = "cc_number")
	private WebElement cardNumberInput;
	@FindBy(id = "cc_month")
	private WebElement dateCaducidadSelect;
	@FindBy(id = "ccYear")
	private WebElement yearSelect;
	@FindBy(id = "cc_cvc")
	private WebElement codeCvcInput;
	@FindBy(name = "book")
	private WebElement bookButton;
	@FindBy(css = "span[class='bui-alert__title']")
	private WebElement errorMessage;
	@FindBy(id = "bp_form_cc_number_msg")
	public WebElement numberCardError;
	@FindBy(id = "bp_form_email_confirm_msg")
	public WebElement securityCodeError;

	public void enterFinalData(String optionCountry, String number, String titularName, String cardType,
			String cardNumber, String CaducidadDate, String caducidadYear, String code) {

		clickButton(regionSelect);
		selectByText(regionSelect, optionCountry);
		setElementText(numberInput, number);
		clearText(titularTarjetaInput);
		setElementText(titularTarjetaInput, titularName);
		clickButton(cardTypeSelect);
		selectByText(cardTypeSelect, cardType);
		setElementText(cardNumberInput, cardNumber);
		clickButton(dateCaducidadSelect);
		clickButton(yearSelect);
		selectByText(yearSelect, caducidadYear);
		setElementText(codeCvcInput, code);
		clickButton(bookButton);

	}

	public boolean iframeVisible() {

		if (casiLoTienesIframe.isDisplayed()) {
			return true;

		} else {
			return false;
		}

	}

	public boolean messageFinalValidation() {
		myWaitMilliseconts(2000);
		return numberCardError.getText().equals(CARD_ERROR) && securityCodeError.getText().equals(CODE_SEC_ERROR);

	}

}
