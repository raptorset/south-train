package com.automation.globan.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultsHotelPage extends BasePage {

	public ResultsHotelPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "a[data-id='class-5']")
	private WebElement cincoEstrellascheckbox;
	@FindBy(css = "#filter_out_of_stock")
	private WebElement disponibilidad;
	@FindBy(css = "div[data-hotelid='256065'] .sr-hotel__name")
	private WebElement hotelName;
	@FindBy(css = ".sr-hotel__name")
	private WebElement allHotelResultTitle;
	@FindBy(css = "div[data-hotelid='256065'] .bui-review-score__badge")
	private WebElement score;
	@FindBy(css = "div[data-hotelid='256065'] div[class='bui-price-display__value prco-inline-block-maker-helper']")
	private WebElement price;
	@FindBy(css = "div[data-hotelid='256065'] span[class='bui-button__text']")
	private WebElement eligeHabitacionButtonSecondPosition;
	@FindBy(css = "div[data-hotelid='256065'] h4")
	public WebElement numberAdultsAndChildsPrevious;
	@FindBy(css = "div[data-hotelid='256065'] .sr-hotel__name")
	public WebElement hotelNameSecondPosition;
	@FindBy(css = "#inspire_filter_block")
	WebElement desayunoIframe;
	@FindBy(css = "span[class='close_inspire_filter_block']")
	WebElement closeIframe;

	public void filterByStart() {

		if (desayunoIframe.isDisplayed()) {
			clickButton(closeIframe);

		}

		scrollToTarget(disponibilidad);
		clickButton(cincoEstrellascheckbox);

	}

	public HotelReservationPage makeReservation() {

		myWaitMilliseconts(3000);
		clickButton(eligeHabitacionButtonSecondPosition);
		myWaitMilliseconts(3000);
		return new HotelReservationPage(getDriver());

	}

	public boolean isElementsEnable() {
		return hotelName.isEnabled() && score.isEnabled() && price.isEnabled();
	}

	public void numeroAdultosNiñosSinSeleccionar() {

	}

}
