package org.pan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

    private final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
            primaryStage.setTitle("幻彩魔方");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("image/color_64.png")));
            primaryStage.show();
        }catch (Exception e){
            LOGGER.error("程序运行时发生严重错误，导致软件退出",e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
