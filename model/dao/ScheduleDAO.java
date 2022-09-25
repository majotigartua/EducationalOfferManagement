package educationaloffermanagement.model.dao;

import educationaloffermanagement.model.DatabaseConnection;
import educationaloffermanagement.model.pojo.Schedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDAO {

    public static ArrayList<Integer> getNRCByEducationalExperience(int idEducationalExperience, String schoolTermCode) throws SQLException {
        ArrayList<Integer> nrc = new ArrayList<>();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = "SELECT * FROM educationalOffer\n"
                + "WHERE educationalOffer.idEducationalExperience = ? AND educationalOffer.schoolTermCode = ?\n";
        try (Connection database = databaseConnection.open()) {
            PreparedStatement configureQuery = database.prepareStatement(query);
            configureQuery.setInt(1, idEducationalExperience);
            configureQuery.setString(2, schoolTermCode);
            ResultSet result = configureQuery.executeQuery();
            while (result.next()) {
                nrc.add(result.getInt("nrc"));
            }
        } catch (SQLException exception) {
            System.err.println(exception.fillInStackTrace());
        } finally {
            databaseConnection.close();
        }
        return nrc;
    }

    public static ArrayList<Schedule> getSchedulesByNRC(int nrc, String schoolTermCode) throws SQLException {
        ArrayList<Schedule> schedules = new ArrayList<>();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = "SELECT * FROM schedule\n"
                + "WHERE nrc = ? AND schoolTermCode = ?";
        try (Connection database = databaseConnection.open()) {
            PreparedStatement configureQuery = database.prepareStatement(query);
            configureQuery.setInt(1, nrc);
            configureQuery.setString(2, schoolTermCode);
            ResultSet result = configureQuery.executeQuery();
            while (result.next()) {
                Schedule schedule = new Schedule();
                schedule.setDay(result.getString("day"));
                schedule.setStartTime(result.getTime("startTime"));
                schedule.setEndTime(result.getTime("endTime"));
                schedule.setRoomNumber(result.getString("roomNumber"));
                schedules.add(schedule);
            }
        } catch (SQLException exception) {
            System.err.println(exception.fillInStackTrace());
        } finally {
            databaseConnection.close();
        }
        return schedules;
    }

}