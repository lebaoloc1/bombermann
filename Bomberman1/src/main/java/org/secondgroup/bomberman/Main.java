package org.secondgroup.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.secondgroup.bomberman.common.Logger;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {
    private static AtomicReference<Logger> logger;

    public static void main(String[] args) {
        logger = new AtomicReference<>();
        var initializer = new Initializer(logger);
        initializer.initialize();

        launch();
    }

    public static Logger getLogger() {
        return logger.get();
    }

    @Override
    public void start(Stage stage) throws IOException {
        var fxmlLoader = new FXMLLoader(Main.class.getResource(
                "view/templates/root-view.fxml"
        ));

        var scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Bomberman");
        stage.setScene(scene);
        stage.show();
    }
}