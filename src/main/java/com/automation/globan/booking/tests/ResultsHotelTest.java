package com.automation.globan.booking.tests;

import static com.automation.globan.booking.utils.Constants.FERRERO_ROCHER;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.automation.globan.booking.pages.BookingHomePage;
import com.automation.globan.booking.pages.HotelReservationPage;
import com.automation.globan.booking.pages.LastStepsPage;
import com.automation.globan.booking.pages.ResultsHotelPage;
import com.automation.globan.booking.pages.WriteYourData;
import com.automation.globan.booking.utils.dates.DataProviderSource;
import com.automation.globan.booking.utils.dates.TestDataModel;

public class ResultsHotelTest extends BaseTest {

	private BookingHomePage bookinHome;
	private ResultsHotelPage resultsPage;
	private HotelReservationPage reserveNow;
	private WriteYourData writeYourData;
	private LastStepsPage lastStep;

	@Test(dataProvider = FERRERO_ROCHER, dataProviderClass = DataProviderSource.class)
	public void verifyResults(HashMap<String, String> dataReservation) {
		TestDataModel dataModel = new TestDataModel();
		bookinHome = new BookingHomePage(driver);
		resultsPage = bookinHome.SearchHotelDeals(dataReservation.get(dataModel.getCity()),
				dataReservation.get(dataModel.getChildAge()));
		resultsPage.filterByStart();
		assertTrue(resultsPage.isElementsEnable());
		reserveNow = resultsPage.makeReservation();
		assertTrue(reserveNow.assertDataReservation());
		assertTrue(reserveNow.isEnableCheckingCheckOut());
		writeYourData = reserveNow.seleccionarReserva(dataReservation.get(dataModel.getNumberRooms()));
		assertTrue(writeYourData.messageValidation());
		lastStep = writeYourData.enterDetails(dataReservation.get(dataModel.getFirstName()),
				dataReservation.get(dataModel.getLastName()), dataReservation.get(dataModel.getEmail()),
				dataReservation.get(dataModel.getEmail()));
		lastStep.enterFinalData(dataReservation.get(dataModel.getOptionCountry()),
				dataReservation.get(dataModel.getNumber()), dataReservation.get(dataModel.getTitularName()),
				dataReservation.get(dataModel.getCardType()), dataReservation.get(dataModel.getCardNumber()),
				dataReservation.get(dataModel.getCaducidadDate()), dataReservation.get(dataModel.getCaducidadYear()),
				dataReservation.get(dataModel.getCode()));

		assertTrue(lastStep.messageFinalValidation());
	}

}
