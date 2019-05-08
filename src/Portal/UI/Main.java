package Portal.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.centerOnScreen();
        primaryStage.setTitle("Portal");
        primaryStage.setScene(root);
        primaryStage.show();
        setPosition(primaryStage,2,2,0,0);
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void setPosition(Stage stage,int heightRatio,int widthRatio,int heightPixel,int widthPixel){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth() + widthPixel) / widthRatio);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight() + heightPixel) / heightRatio);
    }


}
