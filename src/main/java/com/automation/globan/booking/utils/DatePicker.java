package com.automation.globan.booking.utils;

import static java.time.format.TextStyle.FULL;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.stream.LongStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.globan.booking.pages.BasePage;

public class DatePicker extends BasePage {

	public DatePicker(WebDriver driver) {
		super(driver);
	}

	public static final String TIME_ZONE = "America/Bogota";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String DAY_FIRST = "lun, 19 oct";
	private static final String DATE_END = "10 noviembre 2020";
	private static final String DIA = "lun,";
	private static final String SPACE = " ";
	private static final String DAY = "1";

	public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern(DATE_FORMAT);

	@FindBy(css = "div[class='bui-calendar__control bui-calendar__control--prev']")
	private WebElement prev;

	@FindBy(css = "div[class='bui-calendar__control bui-calendar__control--next']")
	private WebElement next;

	@FindBy(css = "div[class='bui-calendar__content'] td")
	private List<WebElement> dates;

	@FindBy(css = "div[class='bui-calendar__content'] :first-of-type div")
	private WebElement currentDate;

	public void setDate(String date) {

		long diff = this.getDateDifferenceInMonths(date);
		int day = this.getDay(date);

		WebElement arrow = (diff >= 0 ? next : prev);
		diff = Math.abs(diff);
		LongStream.range(0, diff).forEach(i -> arrow.click());
		dates.stream().filter(ele -> Integer.parseInt(ele.getText()) == day).findFirst().ifPresent(ele -> ele.click());
	}

	private int getDay(String date) {
		LocalDate dpToDate = LocalDate.parse(date, DTF);
		return dpToDate.getDayOfMonth();
	}

	private long getDateDifferenceInMonths(String date) {
		LocalDate dpCurDate = LocalDate.parse(DAY + SPACE + this.getCurrentMonthFromDatePicker(), DTF);
		System.out.println(dpCurDate);
		LocalDate dpToDate = LocalDate.parse(date, DTF);
		return YearMonth.from(dpCurDate).until(dpToDate, ChronoUnit.MONTHS);
	}

	public String getMonth(int days) {
		Month data = LocalDate.now(ZoneId.of(TIME_ZONE)).plusDays(days).getMonth();
		String month = data.getDisplayName(FULL, new Locale("es", "ES"));
		return month;
	}

	private String getCurrentMonthFromDatePicker() {
		return this.currentDate.getText();
	}

}