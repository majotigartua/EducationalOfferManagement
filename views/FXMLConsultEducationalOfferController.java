package educationaloffermanagement.views;

import educationaloffermanagement.model.dao.EducationalExperienceDAO;
import educationaloffermanagement.model.dao.ScheduleDAO;
import educationaloffermanagement.model.dao.UserDAO;
import educationaloffermanagement.model.pojo.EducationalExperience;
import educationaloffermanagement.model.pojo.SchoolTerm;
import educationaloffermanagement.model.pojo.User;
import educationaloffermanagement.util.Constants;
import educationaloffermanagement.util.Utilities;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLConsultEducationalOfferController implements Initializable {

    @FXML
    private TableView userTableView;
    @FXML
    private ComboBox<EducationalExperience> educationalExperienceComboBox;
    @FXML
    private ComboBox<Integer> nrcComboBox;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn paternalSurnameColumn;
    @FXML
    private TableColumn maternalSurnameColumn;
    @FXML
    private TableColumn institutionalEmailAddressColumn;
    @FXML
    private TableColumn roleColumn;
    @FXML
    private Button consultSchedulesButton;
    @FXML
    private Button acceptButton;

    private User user;
    private SchoolTerm schoolTerm;
    private ObservableList<EducationalExperience> educationalExperiences;
    private ObservableList<Integer> nrc;
    private ObservableList<User> users;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTableView.getSelectionModel().setCellSelectionEnabled(false);
        users = FXCollections.observableArrayList();
        configureTableViewColumns();
    }

    public void configureView(User user, SchoolTerm schoolTerm) throws SQLException {
        this.user = user;
        this.schoolTerm = schoolTerm;
        loadEducationalExperiences();
    }

    @FXML
    private void consultSchedulesButtonClick(ActionEvent event) throws SQLException {
        if (nrcComboBox.getSelectionModel().isEmpty()) {
            Utilities.showAlert("AVISO",
                    "Para consultar un horario de clases, debe seleccionar un NRC.\n\nPor favor, inténtelo nuevamente.",
                    Alert.AlertType.WARNING);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLConsultSchedules.fxml"));
            try {
                Parent root = loader.load();
                FXMLConsultSchedulesController consultSchedulesController = loader.getController();
                consultSchedulesController.configureView(nrcComboBox.getValue(), schoolTerm.getSchoolTermCode());
                Stage consultSchedulesStage = new Stage();
                Scene consultSchedulesView = new Scene(root);
                consultSchedulesStage.setScene(consultSchedulesView);
                consultSchedulesStage.initModality(Modality.APPLICATION_MODAL);
                consultSchedulesStage.setTitle("Consultar horario de clases");
                consultSchedulesStage.showAndWait();
            } catch (IOException exception) {
                System.err.println("Error al cargar la pantalla de 'Consultar horario de clases'...");
            }
        }
    }

    @FXML
    private void acceptButtonClick(ActionEvent event) throws SQLException {
        if (nrcComboBox.getSelectionModel().isEmpty()) {
            Utilities.showAlert("AVISO",
                    "Para inscribir una Experiencia Educativa en el periodo escolar actual, debe seleccionar un NRC.\n\nPor favor, inténtelo nuevamente.",
                    Alert.AlertType.WARNING);
        } else {
            int responseCode = UserDAO.enrollToEducationalExperience(user.getUsername(), nrcComboBox.getValue(), schoolTerm.getSchoolTermCode());
            switch (responseCode) {
                case Constants.CORRECT_OPERATION_CODE:
                    Utilities.showAlert("AVISO",
                            "La información se registró correctamente en el sistema.\n",
                            Alert.AlertType.INFORMATION);
                    users.clear();
                    loadUsersByNRC(nrcComboBox.getValue());
                    break;
                case Constants.ENROLLED_EDUCATIONAL_EXPERIENCE_CODE:
                    Utilities.showAlert("AVISO",
                            "La Experiencia Educativa y NRC seleccionados ya se encuentran inscritos para el periodo escolar actual.\n\nPor favor, inténtelo nuevamente.",
                            Alert.AlertType.WARNING);
                    break;
                default:
                    Utilities.showAlert("ERROR",
                            "No hay conexión con la base de datos. \n\nPor favor, inténtelo más tarde.\n",
                            Alert.AlertType.ERROR);
                    break;
            }
        }
    }

    @FXML
    private void cancelButtonClick(ActionEvent event) {
        goToMainMenu();
    }

    private void configureTableViewColumns() {
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        paternalSurnameColumn.setCellValueFactory(new PropertyValueFactory("paternalSurname"));
        maternalSurnameColumn.setCellValueFactory(new PropertyValueFactory("maternalSurname"));
        institutionalEmailAddressColumn.setCellValueFactory(new PropertyValueFactory("institutionalEmailAddress"));
        roleColumn.setCellValueFactory(new PropertyValueFactory("role"));
    }

    private void loadEducationalExperiences() throws SQLException {
        educationalExperiences = FXCollections.observableArrayList();
        ArrayList<EducationalExperience> educationalExperiencesQuery = EducationalExperienceDAO.getEducationalExperiences(schoolTerm.getSchoolTermCode());
        educationalExperiences.addAll(educationalExperiencesQuery);
        educationalExperienceComboBox.setItems(educationalExperiences);
        educationalExperienceComboBox.valueProperty().addListener(new ChangeListener<EducationalExperience>() {
            @Override
            public void changed(ObservableValue<? extends EducationalExperience> observable, EducationalExperience oldValue, EducationalExperience newValue) {
                nrcComboBox.setDisable(false);
                try {
                   loadSchedulesByEducationalExperience(educationalExperienceComboBox.getSelectionModel().getSelectedItem().getIdEducationalExperience());
                } catch (SQLException exception) {
                    Utilities.showAlert("ERROR",
                            "No hay conexión con la base de datos. \n\nPor favor, inténtelo más tarde.\n",
                            Alert.AlertType.ERROR);
                }
            }
        });
    }

    private void loadSchedulesByEducationalExperience(int idEducationalExperience) throws SQLException {
        nrc = FXCollections.observableArrayList();
        ArrayList<Integer> nrcQuery = ScheduleDAO.getNRCByEducationalExperience(idEducationalExperience, schoolTerm.getSchoolTermCode());
        nrc.addAll(nrcQuery);
        nrcComboBox.setItems(nrc);
        consultSchedulesButton.setDisable(false);
        acceptButton.setDisable(false);
        nrcComboBox.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                users.clear();
                userTableView.setItems(users);
                if (newValue != null) {
                    try {
                        loadUsersByNRC(nrcComboBox.getValue());
                    } catch (SQLException exception) {
                        Utilities.showAlert("ERROR",
                            "No hay conexión con la base de datos. \n\nPor favor, inténtelo más tarde.\n",
                            Alert.AlertType.ERROR);
                    }
                }
            }
        });
    }

    private void loadUsersByNRC(int nrc) throws SQLException {
        userTableView.setItems(users);
        ArrayList<User> usersQuery = UserDAO.getUsersByNRC(nrc);
        users.addAll(usersQuery);
        userTableView.setItems(users);
    }

    private void goToMainMenu() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMainMenu.fxml"));
        try {
            Parent root = loader.load();
            Stage mainStage = (Stage) educationalExperienceComboBox.getScene().getWindow();
            Scene mainMenuView = new Scene(root);
            mainStage.setScene(mainMenuView);
            mainStage.setTitle("Menú principal");
            mainStage.show();
        } catch (IOException exception) {
            System.err.println("Error al cargar la pantalla de 'Menú principal'...");
        }
    }

}