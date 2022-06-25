package uiTests.preconditions;

import java.time.LocalDate;

public class DateTrip {

    private final int TERM_BEFORE_TRIP = 10;
    private final int DURATION_TRIP = 12;

    LocalDate dateNow = LocalDate.now();
    LocalDate dateDeparture = dateNow.plusDays(TERM_BEFORE_TRIP);
    LocalDate dateReturn = dateNow.plusDays(TERM_BEFORE_TRIP + DURATION_TRIP);

    public int monthNumberDeparture = dateDeparture.getMonthValue();
    public int yearDeparture = dateDeparture.getYear();

    public int monthNumberReturn = dateReturn.getMonthValue();
    public int yearReturn = dateReturn.getYear();

    public String stringNumDayDepart = String.valueOf(dateDeparture.getDayOfMonth());
    public String stringNumDayReturn = String.valueOf(dateReturn.getDayOfMonth());

    public String fullDateDepart = stringNumDayDepart + " " + DateParse.getMonth(monthNumberDeparture).toLowerCase() + " " + yearDeparture;
    public String fullDateReturn = stringNumDayReturn + " " + DateParse.getMonth(monthNumberReturn).toLowerCase() + " " + yearReturn;

}
