package educationaloffermanagement.views;

import educationaloffermanagement.model.dao.UserDAO;
import educationaloffermanagement.model.pojo.User;
import educationaloffermanagement.util.Constants;
import educationaloffermanagement.util.Utilities;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label emptyFieldsLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void loginButtonClick(ActionEvent event) throws SQLException {
        emptyFieldsLabel.setText("");
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            emptyFieldsLabel.setText("No se puede dejar ningún campo vacío");
        } else {
            validateLogin(username, password);
        }
    }

    @FXML
    private void registerUserLabelClick(MouseEvent event) {
        goToRegisterUser();
    }

    private void validateLogin(String username, String password) throws SQLException {
        User user = UserDAO.login(username, password);
        switch (user.getResponseCode()) {
            case Constants.CORRECT_LOGIN_CODE:
                Utilities.showAlert("AVISO",
                        "El nombre de usuario y contraseña son correctos. \n\nBienvenido/a al sistema, "
                        + user.getName() + " " + user.getPaternalSurname() + " " + user.getMaternalSurname() + ".\n",
                        Alert.AlertType.INFORMATION);
                goToMainMenu(user);
                break;
            case Constants.INCORRECT_INFORMATION_CODE:
                Utilities.showAlert("AVISO",
                        "Los datos ingresados son inválidos. \n\nPor favor, compruebe la información ingresada e inténtelo nuevamente.\n",
                        Alert.AlertType.WARNING);
                passwordField.setText("");
                break;
            case Constants.DATABASE_CONNECTION_ERROR_CODE:
                Utilities.showAlert("ERROR",
                        "No hay conexión con la base de datos. \n\nPor favor, inténtelo más tarde.\n",
                        Alert.AlertType.ERROR);
                break;
        }
    }

    private void goToMainMenu(User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMainMenu.fxml"));
        try {
            Parent root = loader.load();
            FXMLMainMenuController mainMenuController = loader.getController();
            mainMenuController.configureView(user);
            Stage mainStage = (Stage) usernameTextField.getScene().getWindow();
            Scene mainMenuView = new Scene(root);
            mainStage.setScene(mainMenuView);
            mainStage.setTitle("Menú principal");
            mainStage.show();
        } catch (IOException exception) {
            System.err.println("Error al cargar la pantalla de 'Menú principal'...");
        }
    }

    private void goToRegisterUser() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLRegisterUser.fxml"));
        try {
            Parent root = loader.load();
            Stage registerUserStage = new Stage();
            Scene registerUserView = new Scene(root);
            registerUserStage.setScene(registerUserView);
            registerUserStage.initModality(Modality.APPLICATION_MODAL);
            registerUserStage.setTitle("Registrar user");
            registerUserStage.showAndWait();
        } catch (IOException exception) {
            System.err.println("Error al cargar la pantalla de 'Registrar user'...");
        }
    }

}