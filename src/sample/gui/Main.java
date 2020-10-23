package sample.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    GraphicsContext context;

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WIDTH,HEIGHT);
        context = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake game");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}