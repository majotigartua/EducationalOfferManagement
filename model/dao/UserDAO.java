package educationaloffermanagement.model.dao;

import educationaloffermanagement.model.DatabaseConnection;
import educationaloffermanagement.model.pojo.User;
import educationaloffermanagement.util.Constants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    public static User login(String username, String password) throws SQLException {
        User user = new User();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.open();
        String query = "SELECT user.* FROM user\n"
                + "INNER JOIN role\n"
                + "ON user.idRole = role.idRole\n"
                + "WHERE username = ? AND password = ?";
        try (Connection database = databaseConnection.open()) {
            PreparedStatement configureQuery = database.prepareStatement(query);
            configureQuery.setString(1, username);
            configureQuery.setString(2, password);
            ResultSet result = configureQuery.executeQuery();
            if (result.next()) {
                user.setUsername(result.getString("username"));
                user.setName(result.getString("name"));
                user.setPaternalSurname(result.getString("paternalSurname"));
                user.setMaternalSurname(result.getString("maternalSurname"));
                user.setInstitutionalEmailAddress(result.getString("institutionalEmailAddress"));
                user.setPassword(result.getString("password"));
                user.setIdRole(result.getInt("idRole"));
                user.setResponseCode(Constants.CORRECT_LOGIN_CODE);
            } else {
                user.setResponseCode(Constants.INCORRECT_INFORMATION_CODE);
            }
        } catch (SQLException exception) {
            user.setResponseCode(Constants.DATABASE_CONNECTION_ERROR_CODE);
        } finally {
            databaseConnection.close();
        }
        return user;
    }

    public static int registerUser(User user) throws SQLException {
        int responseCode;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String sentence = "INSERT INTO user (username, name, paternalSurname, maternalSurname,\n"
                + "institutionalEmailAddress, password, idRole)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection database = databaseConnection.open()) {
            PreparedStatement configureSentence = database.prepareStatement(sentence);
            configureSentence.setString(1, user.getUsername());
            configureSentence.setString(2, user.getName());
            configureSentence.setString(3, user.getPaternalSurname());
            configureSentence.setString(4, user.getMaternalSurname());
            configureSentence.setString(5, user.getInstitutionalEmailAddress());
            configureSentence.setString(6, user.getPassword());
            configureSentence.setInt(7, user.getIdRole());
            int affectedRows = configureSentence.executeUpdate();
            responseCode = (affectedRows == 1) ? Constants.CORRECT_OPERATION_CODE : Constants.DATABASE_CONNECTION_ERROR_CODE;
        } catch (SQLException ex) {
            responseCode = Constants.INCORRECT_INFORMATION_CODE;
        } finally {
            databaseConnection.close();
        }
        return responseCode;
    }

    public static ArrayList<User> getUsersByNRC(int nrc) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = "SELECT user.*, role.name AS role FROM user\n"
                + "INNER JOIN role\n"
                + "ON user.idRole = role.idRole\n"
                + "INNER JOIN enrollment\n"
                + "ON user.username = enrollment.username\n"
                + "WHERE enrollment.nrc = ?\n"
                + "ORDER BY role.name DESC";
        try (Connection database = databaseConnection.open()) {
            PreparedStatement configureQuery = database.prepareStatement(query);
            configureQuery.setInt(1, nrc);
            ResultSet result = configureQuery.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setName(result.getString("name"));
                user.setPaternalSurname(result.getString("paternalSurname"));
                user.setMaternalSurname(result.getString("maternalSurname"));
                user.setInstitutionalEmailAddress(result.getString("institutionalEmailAddress"));
                user.setRole(result.getString("role"));
                users.add(user);
            }
        } catch (SQLException exception) {
           System.err.println(exception.fillInStackTrace());
        } finally {
            databaseConnection.close();
        }
        return users;
    }

    public static int enrollToEducationalExperience(String username, int nrc, String schoolTermCode) {
        int responseCode;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String sentence = "INSERT INTO enrollment (username, nrc, schoolTermCode)\n"
                + "VALUES (?, ?, ?)";
        try (Connection database = databaseConnection.open()) {
            PreparedStatement configureSentence = database.prepareStatement(sentence);
            configureSentence.setString(1, username);
            configureSentence.setInt(2, nrc);
            configureSentence.setString(3, schoolTermCode);
            int affectedRows = configureSentence.executeUpdate();
            responseCode = (affectedRows == 1) ? Constants.CORRECT_OPERATION_CODE : Constants.DATABASE_CONNECTION_ERROR_CODE;
        } catch (SQLException exception) {
            responseCode = Constants.ENROLLED_EDUCATIONAL_EXPERIENCE_CODE;
        } finally {
            databaseConnection.close();
        }
        return responseCode;
    } 

}