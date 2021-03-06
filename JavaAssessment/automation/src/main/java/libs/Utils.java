package libs;

import java.util.Random;
import java.sql.*;

// Utils class for global functions
public class Utils {

	private Statement statement = null;
	private ResultSet resultSet = null;
	private Connection connection = null;
	// get relative filepath
	private String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\testData.mdb";
	private String databaseURL = "jdbc:ucanaccess://" + filePath;

	public String getPhoneNumber() {
		// Generate random phone number in specified format
		String phone;
		Random rand = new Random();
		phone = 0 + String.format("%02d", rand.nextInt(100)) + " " + String.format("%03d", rand.nextInt(1000)) + " "
				+ String.format("%04d", rand.nextInt(10000));
		;
		return phone;
	}

	public Object[][] getDataFromDB() {
		// variables
		Object[][] myData;
		// get relative path

		int rowCount = this.getRowCountFromDB();
		myData = new Object[rowCount][4];
		
		String sql;

		int count = 0;

		try {
			// create and get connection
			connection = DriverManager.getConnection(databaseURL);
			// create JDBC Statement
			statement = connection.createStatement();
			// execute SQL to get row count
			sql = "Select count(*) from testData";

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			sql = "SELECT * FROM testData ORDER By TestId";
			resultSet = statement.executeQuery(sql);

			myData = new Object[count][4];
			
			// set data from DB resultset
			count = 0;
			while (resultSet.next()) {
				System.out.println("DB data:" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t"
						+ resultSet.getString(4) + "\t" + resultSet.getString(5));
				myData[count][0] = resultSet.getString(2);
				myData[count][1] = resultSet.getString(3);
				myData[count][2] = resultSet.getString(4);
				myData[count][3] = resultSet.getString(5);
				count++;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return myData;
	}

	public int getRowCountFromDB() {
		// variables
		String sql;

		int count = 0;
		try {

			// create and get connection
			connection = DriverManager.getConnection(databaseURL);
			// create JDBC Statement
			statement = connection.createStatement();
			// execute SQL to get row count
			sql = "SELECT count(*) FROM testData";

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return count;
	}

}
