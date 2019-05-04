package Portal.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene root = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Portal");
        primaryStage.setScene(root);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }




}
