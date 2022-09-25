package educationaloffermanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EducationalOfferManagement extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/FXMLLogin.fxml"));
        Scene loginView = new Scene(root);     
        stage.setScene(loginView);
        stage.setTitle("Iniciar sesión");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}