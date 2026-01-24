package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A singleton class to manage the connection to the database
 * 
 * @author Sam Joyce
 */
public class DBConnection {

	private static DBConnection instance;
	private		   Connection 	connection;
	
    private static final String dbUser 			 = "root";
    private static final String dbPassword  	 = "";
    private static final String dbName      	 = "ShiftManager";
    private static final String employeesTable	 = "employees";
    private static final String shiftsTable		 = "shifts";
    //private static final String supervisorsTable = "supervisors";
    private static final String baseURL     	 = "jdbc:mysql://localhost/";
    private static final String fullURL			 = baseURL + dbName + "?serverTimezone=America/Toronto";
	
    private DBConnection() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	createDataBaseIfNeeded();
    	this.connection = DriverManager.getConnection(fullURL, dbUser, dbPassword);
    }
    
    /**
     * If a connection instance already exists, return it to the caller.
     * Otherwise, create a new instance and return it. 
     * 
     * @return An instance of the database connection.
     * @throws SQLException
     */
    public static synchronized DBConnection getInstance() throws SQLException {
    	if (instance == null) {
    		try {
    			instance = new DBConnection();
    		} catch(ClassNotFoundException e) {
    			throw new SQLException("DB driver not found",e);
    		}
    		
    	}
    	return instance;
    }
    
    /**
     * Makes a connection to the database.
     * 
     * @return A connection to the MySQL database.
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(fullURL, dbUser, dbPassword);
        }
        return connection;
    }
    
    private void createDataBaseIfNeeded() throws SQLException {
    	String createDB	= " CREATE DATABASE IF NOT EXISTS " + dbName 
				   		+ " DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci";
    	String createEmployeesTable 
    			= "CREATE TABLE IF NOT EXISTS " + employeesTable + " ("
    				+ "userId		int			NOT NULL UNIQUE AUTO_INCREMENT, "
    				+ "firstName	varchar(50)	NOT NULL, "
    				+ "lastName		varchar(50)	NOT NULL, "
    				+ "email		varchar(50) NOT NULL, "
    				+ "type			varchar(20)	NOT NULL, "
    				+ "seniority	int, "
    				+ "passHash		varchar(255), "
    				+ "CONSTRAINT employee_PK PRIMARY KEY (userId)"
    			+ " )";
    	
    	String createShiftsTable
    			= "CREATE TABLE IF NOT EXISTS " + shiftsTable + " ("
    				+ "shiftId		int			NOT NULL UNIQUE AUTO_INCREMENT, "
    				+ "start		timestamp	NOT NULL, "
    				+ "end			timestamp	NOT NULL, "
    				+ "userId		int			NULL, "
    				+ "CONSTRAINT shift_PK PRIMARY KEY (shiftId), "
    				+ "CONSTRAINT employee_FK FOREIGN KEY (userId) "
    				+ "REFERENCES employees (userId)"
    			+ ")";
    	
//    	String createSupervisorsTable
//    			= "CREATE TABLE IF NOT EXISTS " + supervisorsTable + "("
//					+ "" // TODO
//				+ ")";
    	
    	try (Connection setupConnection = DriverManager.getConnection(baseURL, dbUser, dbPassword);
    			Statement statement = setupConnection.createStatement()) {
    			statement.executeUpdate(createDB);
    	}

    	try (Connection setupConnection = DriverManager.getConnection(fullURL, dbUser, dbPassword);
    			Statement statement = setupConnection.createStatement()) {
    			// Create the tables
    			statement.executeUpdate(createEmployeesTable);
				statement.executeUpdate(createShiftsTable);
    	}
    }

}
