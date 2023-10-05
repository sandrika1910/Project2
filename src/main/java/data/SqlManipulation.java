package data;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SqlManipulation {
    private static void InsertIntoDatabase(Connection conn, String firstName, String lastName, String phone, String email, Date dateOfBirth, String password) {
        try {
            String sql = "INSERT INTO users (firstName, lastName, phone, email, dateOfBirth, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, email);
            preparedStatement.setDate(5, dateOfBirth);
            preparedStatement.setString(6, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doInsert() {
        try (
                Connection conn = CreateConnection.getConnection()) {
            System.out.println(String.format("Connected to database %s "
                    + "successfully.", conn.getCatalog()));
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2001);
            calendar.set(Calendar.MONTH, Calendar.JUNE);
            calendar.set(Calendar.DAY_OF_MONTH, 9);

            java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
            SqlManipulation.InsertIntoDatabase(conn, "Sandro", "Gagnidze", "595414998", "sandro.gagnidze.2001@gmail.com", sqlDate, "ABdcdd123456@");
        } catch (
                SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteByRow(int rowIdToDelete) {
        try(Connection conn = CreateConnection.getConnection()) {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, rowIdToDelete);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Row with ID " + rowIdToDelete + " deleted successfully.");
            } else {
                System.out.println("Row not found or deletion failed.");
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    public static Map<String, String> returnLastRow() {
        try (Connection connection = CreateConnection.getConnection()) {
            String sql = "SELECT TOP 1 * FROM users ORDER BY id DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int return_id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                Date sqlDate = resultSet.getDate("dateOfBirth");
                String password = resultSet.getString("password");
//                String stringDate = resultSet.getString("dateOfBirth");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = dateFormat.format(sqlDate);
                Map<String, String> map = new HashMap<>();
                map.put("firstName",firstName);
                map.put("lastName",lastName);
                map.put("phone", phone);
                map.put("email", email);
                map.put("dateOfBirth", formattedDate);
                map.put("password", password);
                map.put("id", String.valueOf(return_id));
                System.out.println("firstname -> " + firstName + " lastName " + lastName + " phone " + phone);
                return map;
            } else {
                System.out.println("User not found.");
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return new HashMap<>();
    }
}
