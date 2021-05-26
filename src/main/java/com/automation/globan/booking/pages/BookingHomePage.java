package com.automation.globan.booking.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BookingHomePage extends BasePage {

	public BookingHomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div[class='cross-product-bar__wrapper '] a")
	public List<WebElement> options;
	@FindBy(css = "span[class='xpb__link selected'] ")
	public WebElement dormirOption;
	@FindBy(id = "ss")
	public WebElement selectType;
	@FindBy(css = "div[class='xp__dates-inner']")
	public WebElement dateInput;

	@FindBy(css = "div[class='bui-calendar__content'] :first-of-type table")
	public WebElement checkInCalendar;
	@FindBy(css = "div[class='bui-calendar__content'] :last-of-type table")
	public WebElement checkoutCalendar;

	@FindBy(tagName = "td")
	public WebElement dateWidgetElement;
	@FindBy(css = "div[class='bui-calendar__content'] :first-of-type table tbody tr:nth-last-child(1) td:first-of-type")
	public WebElement dateInitQuemada;
	@FindBy(css = "div[class='bui-calendar__content'] :last-of-type table tbody tr:nth-last-child(4) td:nth-last-child(5)")
	public WebElement dateEndQuemada;

	@FindBy(id = "div[class='bui-calendar__content']")
	private WebElement todoElDataPicker;

	@FindBy(css = "#bui-calendar-1603073034172rxyeb")
	private List<WebElement> mounths;

	@FindBy(css = "[data-bui-ref=\"calendar-next\"]")
	private WebElement next;
	@FindBy(css = "#xp__guests__toggle")
	private WebElement adultosNiñosInput;

	@FindBy(css = "#xp__guests__inputs-container div[class='sb-group__field sb-group__field-adults'] button:last-of-type")
	private WebElement plusAdutosButton;
	@FindBy(css = "#xp__guests__inputs-container div[class='sb-group__field sb-group-children '] button:last-of-type")
	private WebElement plusGroupChildren;
	@FindBy(css = "#xp__guests__inputs-container div[class='sb-group__field sb-group__field-rooms'] button:last-of-type ")
	private WebElement plusNumberRoomns;
	@FindBy(name = "age")
	private WebElement selectAgeChild;
	@FindBy(css = "button[data-sb-id='main']")
	private WebElement buscarButton;

	public ResultsHotelPage SearchHotelDeals(String city, String childAge) {
		setElementText(selectType, city);
		myWaitMilliseconts(2000);
		clickButton(dateInput);
		clickButton(dateInitQuemada);
		clickButton(dateEndQuemada);
		selectAdultos();
		selectAgeChild(childAge);
		clickButton(buscarButton);

		return new ResultsHotelPage(getDriver());

	}

	public void selectAgeChild(String status) {
		clickButton(selectAgeChild);
		Select statusDropdown = new Select(selectAgeChild);
		statusDropdown.selectByVisibleText(status);
	}

	public void selectAdultos() {

		int passengersNumbers = 2;
		clickButton(adultosNiñosInput);

		for (int i = 1; i < passengersNumbers; i++) {
			clickButton(plusAdutosButton);
		}

		int childNumber = 1;
		for (int i = 0; i < childNumber; i++) {
			clickButton(plusGroupChildren);
		}

		int habitaciones = 0;
		for (int i = 0; i < habitaciones; i++) {
			clickButton(plusNumberRoomns);
		}

	}

}
