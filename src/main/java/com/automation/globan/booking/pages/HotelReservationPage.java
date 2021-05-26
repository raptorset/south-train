package com.automation.globan.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelReservationPage extends BasePage {

	private static final String HOTEL_NAME = "GHL Hotel Capital";
	private static final String ADULTS_AND_CHILDS = "Recomendado para 3 adultos, 1 niño";

	public HotelReservationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "table[class='hp-group_recommendation__table'] .totalPrice-container .bui-price-display__label ")
	private WebElement reservationDescription;
	@FindBy(css = "#group_recommendation")
	private WebElement groupRecomendation;
	@FindBy(css = "#hp_hotel_name")
	public WebElement hotelName;
	@FindBy(css = "#group_recommendation h3")
	public WebElement adultsAndChilds;
	@FindBy(css = "h3.top_pick_heading")
	private WebElement title;
	@FindBy(css = "#av-summary-checkin")
	private WebElement checkInReservation;
	@FindBy(css = "#av-summary-checkout")
	private WebElement checkOutReservation;
	@FindBy(css = "a[data-rooms='25606506_266028407_0_1_0:1']")
	private WebElement reservarEstaHabitacionButton;
	@FindBy(css = "#hprt_nos_select_25606506_266028407_0_1_0")
	private WebElement numeroHabitacionesSelect;
	@FindBy(css = "[data-et-click='customGoal:AEaBVcYSYeRT:1']")
	private WebElement reservareButton;

	public WriteYourData seleccionarReserva(String roomsNumber) {

		clickButton(reservarEstaHabitacionButton);
		myWaitMilliseconts(4500);
		clickButton(numeroHabitacionesSelect);
		myWaitMilliseconts(4500);
		selectByValue(numeroHabitacionesSelect, roomsNumber);
		clickButton(reservareButton);
		return new WriteYourData(getDriver());
	}

	public boolean isEnableCheckingCheckOut() {
		scrollToTarget(checkOutReservation);
		return checkInReservation.isEnabled() && checkInReservation.isEnabled();
	}

	public boolean assertDataReservation() {
		switchNextTab();
		if (hotelName.getText().contains(HOTEL_NAME) && adultsAndChilds.getText().contains(ADULTS_AND_CHILDS)) {
			return true;
		}
		return false;
	}

}
