package dao;

import java.util.HashMap;
import java.util.List;

import helper.mysql;

public class schedule {
	public static List<HashMap<String, Object>> getSchedule(String fromCity, String toCity, String startDate,
			String endDate) {
		List<HashMap<String, Object>> list = null;
		String sql = "select `schedule`.ScheduleId,\r\n" + "`schedule`.Date,\r\n" + "`schedule`.Time,\r\n"
				+ "route.DepartureAirportIATA,\r\n" + "DepartureCity.CityName AS DepartCityName,\r\n"
				+ "route.ArrivalAirportIATA,\r\n" + "ArrivalCity.CityName AS ArriveCityName,\r\n"
				+ "aircraft.`Name`,\r\n" + "`schedule`.EconomyPrice,\r\n" + "`schedule`.FlightNumber,\r\n"
				+ "`schedule`.Gate,\r\n" + "`schedule`.`Status`\r\n" + "FROM `schedule`\r\n"
				+ "LEFT JOIN aircraft ON `schedule`.AircraftId = aircraft.AircraftId\r\n"
				+ "LEFT JOIN route ON `schedule`.RouteId = route.RouteId\r\n"
				+ "LEFT JOIN airport AS DepartureAirport ON route.DepartureAirportIATA = DepartureAirport.IATACode\r\n"
				+ "LEFT JOIN city AS DepartureCity ON DepartureAirport.CityCode = DepartureCity.CityCode\r\n"
				+ "LEFT JOIN airport AS ArrivalAirport ON route.ArrivalAirportIATA = ArrivalAirport.IATACode\r\n"
				+ "LEFT JOIN city AS ArrivalCity ON ArrivalAirport.CityCode = ArrivalCity.CityCode\r\n"
				+ "WHERE DepartureCity.CityName = ? AND ArrivalCity.CityName = ? AND\r\n"
				+ "`schedule`.Date BETWEEN ? AND ?";
		list = mysql.query(sql, new Object[] { fromCity, toCity, startDate, endDate }); 
		return list;
	}
	
	public static int updateSchedule(int scheduleId, String status) {
		String sql = "update schedule set Status = ? where ScheduleId = ?";
		int i = mysql.update(sql, new Object[] { status, scheduleId });
		return i;
	}
	
	public static List<HashMap<String, Object>> getTicketStatistics(String startDate, String endDate) {
		List<HashMap<String, Object>> list = null;
		String sql = "SELECT YEAR(Date) AS `Year`,\r\n"
				+ "MONTH(Date) AS `Month`,\r\n"
				+ "COUNT(DISTINCT `schedule`.ScheduleId) AS FlightsAmount,\r\n"
				+ "COUNT(ReservationId) AS TicketsAmount,\r\n"
				+ "SUM(Payment) AS TicketsRevenue\r\n"
				+ "FROM `schedule`\r\n"
				+ "LEFT JOIN flightreservation ON flightreservation.ScheduleId = `schedule`.ScheduleId\r\n"
				+ "WHERE Date BETWEEN ? AND ?\r\n"
				+ "AND `Status` = 'Confirmed'\r\n"
				+ "GROUP BY YEAR(Date),MONTH(Date)\r\n"
				+ "ORDER BY YEAR(Date),MONTH(Date)";
		list = mysql.query(sql, new Object[] { startDate, endDate });
		return list;
	}
}
