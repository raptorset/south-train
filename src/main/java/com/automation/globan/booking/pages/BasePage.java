package com.automation.globan.booking.pages;

import static java.time.Duration.ofSeconds;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.getInstance;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.automation.globan.booking.utils.DatePicker;
import com.automation.globan.booking.utils.MyCustomWait;

public class BasePage {
	protected static WebDriver driver;
	protected DatePicker dataPicker;
	protected MyCustomWait customWait = new MyCustomWait();
	private static final int NEXT_TAB = 1;
	private static final int PREV_TAB = 1;
	public static final String ZONE_ID = "America/Bogota";
	public static final String FORMAT_DATA = "yyyy-MM-dd";

	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	protected static void clickButton(WebElement button) {
		button.click();
	}

	protected static void setElementText(WebElement inputElement, String value) {
		inputElement.sendKeys(value);
	}

	public void scrollToTarget(WebElement we) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,350)", we);

	}

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,550)", "");

	}

	public void switchNextTab() {
		ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(NEXT_TAB));
	}

	public void switchPreviousTab() {
		ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(PREV_TAB));
	}

	public void clearText(WebElement element) {
		element.clear();
	}

	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(10, SECONDS);

	}

	public void myWaitMilliseconts(int milisegundos) {
		Calendar calendar = getInstance();
		Date hora = new Date();
		calendar.setTime(hora);
		if (milisegundos >= 1000) {
			calendar.add(SECOND, (milisegundos / 1000));
		} else if (milisegundos < 1000) {
			calendar.add(MILLISECOND, (milisegundos));
		}
		while (hora.compareTo(calendar.getTime()) < 0) {
			hora = new Date();

		}
	}

	/**
	 * @param current zone + days
	 * @return
	 */
	public String getAllData(int days) {
		LocalDate data = LocalDate.now(ZoneId.of(ZONE_ID)).plusDays(days);
		String formData = data.format(DateTimeFormatter.ofPattern(FORMAT_DATA));
		return formData;
	}

	public WebElement fluentWaitTarget(WebElement element) {
		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class, TimeoutException.class)
				.ignoring(StaleElementReferenceException.class);

		for (int i = 0; i < 4; i++) {
			fWait.until(visibilityOf(element));
			fWait.until(elementToBeClickable(element));
		}
		return element;
	}

	public void selectByText(WebElement element, String option) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(option);
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String addDaysForward(String currentDate) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATA);
		Calendar cal = Calendar.getInstance();
		cal.setTime(format.parse(currentDate));
		cal.add(Calendar.DAY_OF_YEAR, 30);
		String dat = format.format(cal.getTime());
		return dat;

	}

	public void selectByValue(WebElement element, String option) {
		Select dropdown = new Select(element);
		dropdown.selectByValue(option);
	}

}