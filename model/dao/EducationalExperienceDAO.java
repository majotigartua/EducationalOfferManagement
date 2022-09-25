package educationaloffermanagement.model.dao;

import educationaloffermanagement.model.DatabaseConnection;
import educationaloffermanagement.model.pojo.EducationalExperience;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EducationalExperienceDAO {

    public static ArrayList<EducationalExperience> getEducationalExperiences(String schoolTermCode) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ArrayList<EducationalExperience> educationalExperiences = new ArrayList<>();
        databaseConnection.open();
        String query = "SELECT DISTINCT educationalExperience.* FROM educationalExperience \n"
                + "INNER JOIN educationalOffer\n"
                + "ON educationalExperience.idEducationalExperience = educationalOffer.idEducationalExperience\n"
                + "WHERE educationalOffer.schoolTermCode = ?\n"
                + "ORDER BY (educationalExperience.name) ASC";
        try (Connection database = databaseConnection.open()){
            PreparedStatement configureQuery = database.prepareStatement(query);
            configureQuery.setString(1, schoolTermCode);
            ResultSet result = configureQuery.executeQuery();
            while (result.next()) {
                EducationalExperience educationalExperience = new EducationalExperience();
                educationalExperience.setIdEducationalExperience(result.getInt("idEducationalExperience"));
                educationalExperience.setName(result.getString("name"));
                educationalExperiences.add(educationalExperience);
            }
        } catch (SQLException exception) {
            System.err.println(exception.fillInStackTrace());
        } finally {
            databaseConnection.close();
        }
        return educationalExperiences;
    }

}