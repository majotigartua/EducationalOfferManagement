package educationaloffermanagement.model.dao;

import educationaloffermanagement.model.DatabaseConnection;
import educationaloffermanagement.model.pojo.SchoolTerm;
import educationaloffermanagement.util.Constants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchoolTermDAO {

    public static SchoolTerm getSchoolTerm() throws SQLException {
        SchoolTerm schoolTerm = new SchoolTerm();
        DatabaseConnection databaseConnection = new DatabaseConnection();
            String consulta = "SELECT * FROM schoolTerm\n"
                    + "WHERE (NOW() BETWEEN schoolTerm.startDate AND schoolTerm.endDate)";
            try (Connection database = databaseConnection.open()) {
                PreparedStatement configureQuery = database.prepareStatement(consulta);
                ResultSet resultado = configureQuery.executeQuery();
                if (resultado.next()) {
                    schoolTerm.setSchoolTermCode(resultado.getString("schoolTermCode"));
                    schoolTerm.setStartDate(resultado.getDate("startDate"));
                    schoolTerm.setEndDate(resultado.getDate("endDate"));
                }
            } catch (SQLException exception) {
                schoolTerm.setResponseCode(Constants.DATABASE_CONNECTION_ERROR_CODE);
            } finally {
                databaseConnection.close();
            }
        return schoolTerm;
    }

}