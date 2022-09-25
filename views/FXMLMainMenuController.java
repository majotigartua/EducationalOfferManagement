package educationaloffermanagement.views;

import educationaloffermanagement.model.dao.SchoolTermDAO;
import educationaloffermanagement.model.pojo.SchoolTerm;
import educationaloffermanagement.model.pojo.User;
import educationaloffermanagement.util.Utilities;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class FXMLMainMenuController implements Initializable {

    private static SchoolTerm schoolTerm;
    private static User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            schoolTerm = SchoolTermDAO.getSchoolTerm();
        } catch (SQLException exception) {
            Utilities.showAlert("ERROR",
                    "No hay conexión con la base de datos. \n\nPor favor, inténtelo más tarde.\n",
                    Alert.AlertType.ERROR);
        }
    }

    public void configureView(User user) {
        this.user = user;
    }

    @FXML
    private void consultEducationalOfferClick(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLConsultEducationalOffer.fxml"));
        try {
            Parent root = loader.load();
            FXMLConsultEducationalOfferController consultEducationalOfferController = loader.getController();
            consultEducationalOfferController.configureView(user, schoolTerm);
            Stage mainStage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene consultEducationalOfferView = new Scene(root);
            mainStage.setScene(consultEducationalOfferView);
            mainStage.setTitle("Consultar oferta educativa");
            mainStage.show();
        } catch (IOException exception) {
            System.err.println("Error al cargar la pantalla de 'Consultar oferta educativa'...");
        }
    }

    @FXML
    private void logoutButtonClick(ActionEvent event) {
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Scene loginView = new Scene(FXMLLoader.load(getClass().getResource("FXMLLogin.fxml")));
            mainStage.setScene(loginView);
            mainStage.setTitle("Iniciar sesión");
            mainStage.show();
        } catch (IOException exception) {
            System.err.println("Error al cargar la pantalla de 'Iniciar sesión'...");
        }
    }

}