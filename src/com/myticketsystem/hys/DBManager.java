package com.myticketsystem.hys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

public class DBManager {

	public static Connection connection;
	
	public final static String DB_HOST = "localhost";
	public final static String DB_USER = "root";
	public final static String DB_PASS = "12345678";
	public final static String DB_DB = "tickets";
	public final static String DB_PORT = "3306";


	public static Concert addConcert(Concert concert) {
		// returns Concert to get the new ID in the DB
		initDB();

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO concerts (title,description,ticket_price,date,venue) VALUES (?,?,?,?,?)");
			preparedStatement.setString(1, concert.getTitle());
			preparedStatement.setString(2, concert.getDescription());
			preparedStatement.setDouble(3, concert.getPrice());
			preparedStatement.setString(4, Helper.getFormattedDate(GlobalVariables.DEFAULT_DATE_FORMAT, concert.getcDate()));
			preparedStatement.setString(5, concert.getVenue());

			preparedStatement.executeUpdate();
			preparedStatement.close();

			// get new id
			preparedStatement = connection.prepareStatement("SELECT * FROM concerts order by id DESC LIMIT 1;");

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				// if cause only one result if there is a result
				concert.setId(rs.getInt("id"));
			}

			return concert;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return null;
	}

	public static void addTransaction(Transaction transaction) {
		initDB();

		final String sql = "INSERT INTO transactions (customer_name,transaction_id,ticket_type,ticket_price,concert_name,concert_seat,concert_venue,transaction_date,concert_date) VALUES (?,?,?,?,?,?,?,?,?)";

		executeUpdate(sql,
				new Object[] { transaction.getCustomerName(), transaction.getTransactionId(), transaction.getTicketType(),
						transaction.getTicketPrice(), transaction.getConcertName(), transaction.getConcertSeat(),
						transaction.getConcertVenue(), transaction.getTransactionDate(), transaction.getConcertDate() });
	}

	public static boolean concertExists(Concert concert) {
		// checks if ID exists
		initDB();

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM concerts where id = ? LIMIT 1");
			preparedStatement.setInt(1, concert.getId());
			ResultSet rs = preparedStatement.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return false;
	}

	public static ResultSet executeQuery(String sql, Object[] vars) {
		initDB();

		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement = setStatementVars(preparedStatement, vars);

			ResultSet rs = preparedStatement.executeQuery();

			return rs;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

		return null;
	}

	public static void executeUpdate(String sql, Object[] vars) {
		initDB();

		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement = setStatementVars(preparedStatement, vars);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

	}

	public static LinkedHashMap<String, Concert> getAllConcerts() {

		initDB();

		PreparedStatement preparedStatement = null;

		try {
			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM concerts");// preparedStatement.executeQuery();

			LinkedHashMap<String, Concert> concertMap = new LinkedHashMap<String, Concert>();
			while (rs.next()) {
				// Functions.Log("Concert " + rs.getString("title"));

				final String tempTitle = rs.getString("title");
				final String tempDescription = rs.getString("description");
				final Double tempPrice = rs.getDouble("ticket_price");
				final String tempDate = rs.getString("date");
				final String tempVenue = rs.getString("venue");
				final int tempId = rs.getInt("id");

				// Helper.Log(tempTitle);

				SimpleDateFormat dt = new SimpleDateFormat(GlobalVariables.DEFAULT_DATE_FORMAT);

				try {
					Date tempFinalDate = dt.parse(tempDate);
					Concert c = new Concert(tempTitle, tempDescription, tempPrice, tempFinalDate, tempVenue, tempId);
					concertMap.put(tempTitle, c);
					// System.out.println(tempFinalDate.toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			rs.close();
			stmt.close();

			return concertMap;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException se2) {

			}
		}

		return new LinkedHashMap<String, Concert>();
	}

	public static User getUser(String username, String password) {

		initDB();

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection
					.prepareStatement("SELECT * FROM users where username = ? AND password = ? AND enabled=1 LIMIT 1");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();

			User usr = null;

			while (rs.next()) {
				usr = new User(rs.getString("username"), rs.getString("password"), rs.getString("user_type"),
						rs.getBoolean("enabled"));
			}

			rs.close();
			preparedStatement.close();

			return usr;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return null;
	}
	
	public static Concert getConcertByID(int id) {

		initDB();

		PreparedStatement preparedStatement = null;

		try {
			PreparedStatement stmt = connection.prepareStatement("select * from concerts where id=?");
			stmt = setStatementVars(stmt, new Object[]{1});
			ResultSet rs = stmt.executeQuery();
			Concert c = null;
			if (rs.next()) {

				final String tempTitle = rs.getString("title");
				final String tempDescription = rs.getString("description");
				final Double tempPrice = rs.getDouble("ticket_price");
				final String tempDate = rs.getString("date");
				final String tempVenue = rs.getString("venue");
				final int tempId = rs.getInt("id");

				// Helper.Log(tempTitle);

				SimpleDateFormat dt = new SimpleDateFormat(GlobalVariables.DEFAULT_DATE_FORMAT);

				try {
					Date tempFinalDate = dt.parse(tempDate);
					c = new Concert(tempTitle, tempDescription, tempPrice, tempFinalDate, tempVenue, tempId);
					return c;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			rs.close();
			stmt.close();
			
			return c;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return null;
	}

	public static void initDB() {

		String connectionUrl = String.format("jdbc:mysql://%s:%s/%s", DB_HOST, DB_PORT, DB_DB);

		try {
			if ((connection != null) && !connection.isClosed()) {
				// Helper.Log("DB connection is alive, no need to connect again");
				return;
			}

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionUrl, DB_USER, DB_PASS);
			// Helper.Log("Successfully connected to DB");

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removeConcert(Concert concert) {

		initDB();

		if (!concertExists(concert)) {
			return;
		}

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("delete from concerts WHERE id=?");
			preparedStatement.setInt(1, concert.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static PreparedStatement setStatementVars(PreparedStatement preparedStatement, Object[] vars) {
		try {
			for (int i = 0; i < vars.length; i++) {
				Object temp = vars[i];

				if (temp instanceof String) {
					preparedStatement.setString(i + 1, (String) temp);
				} else if (temp instanceof Integer) {
					preparedStatement.setInt(i + 1, (Integer) temp);
				} else if (temp instanceof Double) {
					preparedStatement.setDouble(i + 1, (Double) temp);
				} else {
				}
			}

			return preparedStatement;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return preparedStatement;

	}

	public static void updateConcert(Concert concert) {

		initDB();

		if (!concertExists(concert)) {
			addConcert(concert);
		}

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE concerts SET title=?,description=?,ticket_price=?,date=?,venue=? WHERE id=?");
			preparedStatement.setString(1, concert.getTitle());
			preparedStatement.setString(2, concert.getDescription());
			preparedStatement.setDouble(3, concert.getPrice());
			preparedStatement.setString(4, Helper.getFormattedDate(GlobalVariables.DEFAULT_DATE_FORMAT, concert.getcDate()));
			preparedStatement.setString(5, concert.getVenue());
			preparedStatement.setInt(6, concert.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
