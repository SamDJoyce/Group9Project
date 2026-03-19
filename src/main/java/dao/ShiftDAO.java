package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import services.ShiftService;
import shifts.Shift;
import shifts.ShiftStatusFactory;
import users.Employee;
import users.UserFactory;

/**
 * Mediates database interactions for Shift objects.
 * Implemented for MySQL 8.0
 * 
 * @author Sam Joyce
 */
public class ShiftDAO implements ShiftService {

	public static final String shiftsTable = "shifts";
	public static final String employeesTable = "employees";
	private static DayOfWeek FIRST_DAY = DayOfWeek.MONDAY;
	
	public ShiftDAO() {
	}

	/**
	 * Generate a new, open shift, insert into the database,
	 * add generated ID, then return the Shift object.
	 * 
	 * @param	Date and Time the shift begins.
	 * @param	Data and Time the shift is complete.
	 */
	@Override
	public Shift createShift(LocalDateTime start, LocalDateTime end) {
		Shift shift = new Shift.Builder()
							   .setStart(start)
							   .setEnd(end)
							   .build();
		String newShift = "INSERT INTO " + shiftsTable + " ("
						+ "start, end, status ) "
						+ "VALUES (?,?,?)";
		Connection 		  connection    = null;
		PreparedStatement statement     = null;
		ResultSet		  generatedKeys = null;
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement = connection.prepareStatement(newShift,  Statement.RETURN_GENERATED_KEYS);
			
			statement.setTimestamp(1, Timestamp.valueOf(start));
			statement.setTimestamp(2, Timestamp.valueOf(end));
			statement.setString   (3, shift.getStatus().toString());
			
			statement.executeUpdate();
			generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				int generatedShiftID = generatedKeys.getInt(1);
				shift.setShiftId(generatedShiftID);
			}
			// Return the new User object
			return shift;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if (generatedKeys != null) {
					generatedKeys.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public Shift createShift(LocalDateTime start, LocalDateTime end, Employee employee) {
		Shift shift = new Shift.Builder()
				   			   .setStart(start)
				   			   .setEnd(end)
				   			   .assignEmployee(employee)
				   			   .build();
		String newShift = "INSERT INTO " + shiftsTable + " ("
						+ "start, end, status, userId ) "
						+ "VALUES (?,?,?,?)";
		
		Connection 		  connection    = null;
		PreparedStatement statement     = null;
		ResultSet		  generatedKeys = null;
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement = connection.prepareStatement(newShift,  Statement.RETURN_GENERATED_KEYS);
			
			statement.setTimestamp(1, Timestamp.valueOf(start));
			statement.setTimestamp(2, Timestamp.valueOf(end));
			statement.setString   (3, shift.getStatus().toString());
			statement.setInt	  (4, employee.getUserId());
			
			statement.executeUpdate();
			generatedKeys = statement.getGeneratedKeys();
			
			if(generatedKeys.next()) {
				int generatedShiftID = generatedKeys.getInt(1);
				shift.setShiftId(generatedShiftID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if (generatedKeys != null) {
					generatedKeys.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shift;
	}

	@Override
	public void deleteShift(int shiftId) {
	    String deleteShift = "DELETE FROM " + shiftsTable + " WHERE shiftId = ?";

	    try (Connection connection = DBConnection.getInstance().getConnection();
	         PreparedStatement statement = connection.prepareStatement(deleteShift)) {

	        statement.setInt(1, shiftId);
	        statement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}

	@Override
	public Shift getShift(int shiftId) {
		Shift shift = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resSet = null;
		String getShift = "SELECT * FROM " + shiftsTable + " WHERE shiftId = ? "
						+ "LEFT JOIN " + employeesTable + " "
						+ "ON " + shiftsTable + ".userId = " 
								+ employeesTable + ".userId";
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement  = connection.prepareStatement(getShift);
			statement.setInt(1, shiftId);
			resSet = statement.executeQuery();
			if (resSet.next()) {
				shift = new Shift.Builder()
						.setShiftId(shiftId)
						.setStart(resSet.getTimestamp("start").toLocalDateTime())
						.setEnd  (resSet.getTimestamp("end").toLocalDateTime())
						.setStatus(ShiftStatusFactory.get(resSet.getString("status")))
						.build();
				if (resSet.getInt("userId") != 0) {
					shift.assignEmployee (UserFactory.get(
											resSet.getInt   ("userId"), 
											resSet.getString("firstName"), 
											resSet.getString("lastName"), 
											resSet.getString("email"), 
											resSet.getString("type"), 
											resSet.getInt   ("seniority"), 
											resSet.getString("passHash")));
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resSet!=null) {
					resSet.close();
				}
				if (statement!=null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shift;
	}

	@Override
	public List<Shift> getShiftsByDay(LocalDate date) {
		List<Shift> shifts = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resSet = null;
		String getShifts = "SELECT * FROM " + shiftsTable + " "
						 + "WHERE start >= ? "
						 + "AND start < ? "
						 + "LEFT JOIN " + employeesTable + " "
							+ "ON " + shiftsTable + ".userId = " 
									+ employeesTable + ".userId";
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement  = connection.prepareStatement(getShifts);
			
	        Timestamp startOfDay = Timestamp.valueOf(date.atStartOfDay());
	        Timestamp startOfNextDay = Timestamp.valueOf(date.plusDays(1).atStartOfDay());
	        Shift shift = null;
			
	        statement.setTimestamp(1, startOfDay);
	        statement.setTimestamp(2, startOfNextDay);
			resSet = statement.executeQuery();
			
			while(resSet.next()) {
				shift = new Shift.Builder()
								 .setShiftId(resSet.getInt("shiftId"))
								 .setStart(resSet.getTimestamp("start").toLocalDateTime())
								 .setEnd(resSet.getTimestamp("end").toLocalDateTime())
								 .setStatus(ShiftStatusFactory.get(resSet.getString("status")))
								 .build();
				if (resSet.getInt("userId") != 0) {
					 shift.assignEmployee(UserFactory.get(
											resSet.getInt   ("userId"), 
											resSet.getString("firstName"), 
											resSet.getString("lastName"), 
											resSet.getString("email"), 
											resSet.getString("type"), 
											resSet.getInt   ("seniority"), 
											resSet.getString("passHash") ));
				}
				shifts.add(shift);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resSet!=null) {
					resSet.close();
				}
				if (statement!=null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shifts;
	}
	
	@Override
	public List<Shift> getShiftsByDay(LocalDate date, Employee employee){
		List<Shift> shifts = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resSet = null;
		String getShifts = "SELECT * FROM " + shiftsTable + " "
						 + "WHERE start >= ? "
						 + "AND start < ? "
						 + "AND userId = ?";
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement  = connection.prepareStatement(getShifts);
			
	        Timestamp startOfDay = Timestamp.valueOf(date.atStartOfDay());
	        Timestamp startOfNextDay = Timestamp.valueOf(date.plusDays(1).atStartOfDay());
	        Shift shift = null;
			
	        statement.setTimestamp(1, startOfDay);
	        statement.setTimestamp(2, startOfNextDay);
	        statement.setInt	  (3, employee.getUserId());
			resSet = statement.executeQuery();
			
			while(resSet.next()) {
				shift = new Shift.Builder()
								 .setShiftId(resSet.getInt("shiftId"))
								 .setStart(resSet.getTimestamp("start").toLocalDateTime())
								 .setEnd(resSet.getTimestamp("end").toLocalDateTime())
								 .setStatus(ShiftStatusFactory.get(resSet.getString("status")))
								 .assignEmployee(employee)
								 .build();
				shifts.add(shift);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resSet!=null) {
					resSet.close();
				}
				if (statement!=null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shifts;
	}
	
	@Override
	public List<Shift> getShiftsByWeek(LocalDate weekOf) {
		List<Shift> shifts = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resSet = null;
		String getShifts = "SELECT * FROM " + shiftsTable + " "
						 + "WHERE start >= ? "
						 + "AND start < ? ";
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement  = connection.prepareStatement(getShifts);
			// rollback the day
			LocalDate firstDay = weekOf.with(
					 TemporalAdjusters.previousOrSame(FIRST_DAY) );
	        Timestamp startOfWeek = Timestamp.valueOf(firstDay.atStartOfDay());
	        Timestamp startOfNextWeek = Timestamp.valueOf(firstDay.plusDays(7).atStartOfDay());
	        Shift shift = null;
			
	        statement.setTimestamp(1, startOfWeek);
	        statement.setTimestamp(2, startOfNextWeek);
			resSet = statement.executeQuery();
			
			while(resSet.next()) {
				shift = new Shift.Builder()
								 .setShiftId(resSet.getInt("shiftId"))
								 .setStart(resSet.getTimestamp("start").toLocalDateTime())
								 .setEnd(resSet.getTimestamp("end").toLocalDateTime())
								 .setStatus(ShiftStatusFactory.get(resSet.getString("status")))
								 .build();
				if (resSet.getInt("userId") != 0) {
					 shift.assignEmployee(UserFactory.get(
											resSet.getInt   ("userId"), 
											resSet.getString("firstName"), 
											resSet.getString("lastName"), 
											resSet.getString("email"), 
											resSet.getString("type"), 
											resSet.getInt   ("seniority"), 
											resSet.getString("passHash") ));
				}
				shifts.add(shift);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resSet!=null) {
					resSet.close();
				}
				if (statement!=null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shifts;
	}
	
	@Override
	public List<Shift> getShiftsByWeek(LocalDate weekOf, Employee employee) {
		List<Shift> shifts = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resSet = null;
		String getShifts = "SELECT * FROM " + shiftsTable + " "
						 + "WHERE start >= ? "
						 + "AND start < ? "
						 + "AND userId = ?";
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement  = connection.prepareStatement(getShifts);
			// rollback the day
			LocalDate firstDay = weekOf.with(
					 TemporalAdjusters.previousOrSame(FIRST_DAY) );
	        Timestamp startOfWeek = Timestamp.valueOf(firstDay.atStartOfDay());
	        Timestamp startOfNextWeek = Timestamp.valueOf(firstDay.plusDays(7).atStartOfDay());
	        Shift shift = null;
			
	        statement.setTimestamp(1, startOfWeek);
	        statement.setTimestamp(2, startOfNextWeek);
	        statement.setInt	  (3, employee.getUserId());
			resSet = statement.executeQuery();
			
			while(resSet.next()) {
				shift = new Shift.Builder()
								 .setShiftId(resSet.getInt("shiftId"))
								 .setStart(resSet.getTimestamp("start").toLocalDateTime())
								 .setEnd(resSet.getTimestamp("end").toLocalDateTime())
								 .setStatus(ShiftStatusFactory.get(resSet.getString("status")))
								 .assignEmployee(employee)
								 .build();
				shifts.add(shift);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resSet!=null) {
					resSet.close();
				}
				if (statement!=null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shifts;
	}

	@Override
	public void updateShift(Shift shift) {
		String newShift = "UPDATE " + shiftsTable + " SET "
						+ "start = ?,"
						+ "end = ?, "
						+ "status = ?, "
						+ "userId = ? "
						+ "WHERE shiftId = ?";
		Connection 		  connection    = null;
		PreparedStatement statement     = null;
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement = connection.prepareStatement(newShift,  Statement.RETURN_GENERATED_KEYS);
			
			statement.setTimestamp(1, Timestamp.valueOf(shift.getStart()));
			statement.setTimestamp(2, Timestamp.valueOf(shift.getEnd()));
			statement.setString   (3, shift.getStatus().toString());
			statement.setInt	  (4, shift.getEmployee() != null ? shift.getEmployee().getUserId() : 0);
			statement.setInt	  (5, shift.getShiftId());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
