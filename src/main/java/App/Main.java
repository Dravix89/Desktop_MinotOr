package App;

import com.mongodb.client.MongoDatabase;
import com.minotor.desktop_minotor.service.MongoDBConnector;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public Main() {
    }


    @Override
    public void start(Stage stage) throws IOException {
        if (MongoDBConnector.testConnection()) {
            System.out.println("MongoDB OK");
        } else {
            System.err.println("MongoDB KO");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(App.Main.class.getResource("/View/login.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load());
        stage.setTitle("Minot'Or Desktop");
        stage.setScene(scene);
        stage.getIcons().add(new Image(App.Main.class.getResourceAsStream("/Images/logoOr.png")));
        stage.show();
    }

    @Override
    public void stop() {
        MongoDBConnector.close();
    }



    public static void main(String[] args) {
        launch(new String[0]);
    }
}