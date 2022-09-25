package educationaloffermanagement.views;

import educationaloffermanagement.model.dao.UserDAO;
import educationaloffermanagement.model.pojo.User;
import educationaloffermanagement.util.Constants;
import educationaloffermanagement.util.Utilities;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLRegisterUserController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private Label emptyFieldsLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField paternalSurnameTextField;
    @FXML
    private TextField maternalSurnameTextField;
    @FXML
    private TextField institutionalEmailAddressTextField;
    @FXML
    private RadioButton professorRadioButton;
    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void cancelButtonClick(ActionEvent event) {
        closePopUpWindow();
    }

    @FXML
    private void acceptButtonClick(ActionEvent event) throws SQLException {
        emptyFieldsLabel.setText("");
        String username = usernameTextField.getText();
        String name = nameTextField.getText();
        String paternalSurname = paternalSurnameTextField.getText();
        String maternalSurname = maternalSurnameTextField.getText();
        String institutionalEmailAddress = institutionalEmailAddressTextField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || name.isEmpty() || paternalSurname.isEmpty() || maternalSurname.isEmpty()
                || institutionalEmailAddress.isEmpty() || password.isEmpty()) {
            emptyFieldsLabel.setText("No se puede dejar ningún campo vacío.");
        } else {
            int idRol = (professorRadioButton.isSelected()) ? Constants.ID_PROFESSOR_ROLE : Constants.ID_STUDENT_ROLE;
            User user = new User(username, name, paternalSurname, maternalSurname,
                    institutionalEmailAddress, password, idRol);
            registerUser(user);
        }
    }

    private void registerUser(User user) throws SQLException {
        int responseCode = UserDAO.registerUser(user);
        switch (responseCode) {
            case Constants.CORRECT_OPERATION_CODE:
                Utilities.showAlert("AVISO",
                        "La información se registró correctamente en el sistema.\n",
                        Alert.AlertType.INFORMATION);
                closePopUpWindow();
                break;
            case Constants.INCORRECT_INFORMATION_CODE:
                Utilities.showAlert("AVISO",
                        "La información ingresada corresponde a un user que ya se encuentra registrado en el sistema.\n\nPor favor, inténtelo nuevamente.",
                        Alert.AlertType.WARNING);
                break;
            case Constants.DATABASE_CONNECTION_ERROR_CODE:
                Utilities.showAlert("ERROR",
                        "No hay conexión con la base de datos. \n\nPor favor, inténtelo más tarde.\n",
                        Alert.AlertType.ERROR);
                closePopUpWindow();
                break;
        }
    }

    private void closePopUpWindow() {
        Stage mainStage = (Stage) emptyFieldsLabel.getScene().getWindow();
        mainStage.close();
    }

}