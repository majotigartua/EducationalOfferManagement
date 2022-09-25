package educationaloffermanagement.views;

import educationaloffermanagement.model.dao.ScheduleDAO;
import educationaloffermanagement.model.pojo.Schedule;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FXMLConsultSchedulesController implements Initializable {

    @FXML
    private TableView<Schedule> schedulesTableView;
    @FXML
    private TableColumn dayColumn;
    @FXML
    private TableColumn startTimeColumn;
    @FXML
    private TableColumn endTimeColumn;
    @FXML
    private TableColumn roomNumberColumn;

    private int nrc;
    private String schoolTermCode;
    private ObservableList<Schedule> schedules;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureTableViewColumns();
    }

    public void configureView(int nrc, String schoolTermCode) throws SQLException {
        this.nrc = nrc;
        this.schoolTermCode = schoolTermCode;
        loadSchedulesByNRC();
    }

    @FXML
    private void cancelButtonClick(ActionEvent event) {
        Stage mainStage = (Stage) schedulesTableView.getScene().getWindow();
        mainStage.close();
    }

    private void configureTableViewColumns() {
        dayColumn.setCellValueFactory(new PropertyValueFactory("day"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory("endTime"));
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory("roomNumber"));
    }

    private void loadSchedulesByNRC() throws SQLException {
        schedules = FXCollections.observableArrayList();
        ArrayList<Schedule> schedulesQuery = ScheduleDAO.getSchedulesByNRC(nrc, schoolTermCode);
        schedules.addAll(schedulesQuery);
        schedulesTableView.setItems(schedules);
    }
    
}