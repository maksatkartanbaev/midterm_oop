package sample.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.heart.GameLoop;
import sample.heart.Grid;
import sample.heart.Snake;

public class Main extends Application {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    private GameLoop loop;
    private Grid grid;
    private GraphicsContext context;

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WIDTH,HEIGHT);
        context = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            Snake snake = grid.getSnake();
            if(loop.isKeyPressed()){
                return;
            }
            switch (e.getCode()) {
                case UP -> snake.setUp();
                case DOWN -> snake.setDown();
                case LEFT -> snake.setLeft();
                case RIGHT -> snake.setRight();
                case ESCAPE -> System.exit(0);
            }
        });

        reset();

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake game");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();

        (new Thread(loop)).start();
    }

    private void reset(){
        grid = new Grid(WIDTH, HEIGHT);
        loop = new GameLoop(grid, context);
        Painter.paint(grid, context);
    }

    public static void main(String[] args) {
        launch(args);
    }
}