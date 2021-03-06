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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    private GameLoop loop;
    private Grid grid;
    private GraphicsContext context;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            File myObj = new File("Highscore.txt");
            if(myObj.createNewFile()) {
                System.out.println("File successfully created");
                try{
                    FileWriter myWriter = new FileWriter("Highscore.txt");
                    myWriter.write('0');
                    myWriter.close();
                    System.out.println("Successfully wrote to file");
                } catch (IOException e){
                    System.out.println("Error");
                    e.printStackTrace();
                }
            } else{
                System.out.println("File already exists");
            }
        } catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
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
                case ENTER -> {
                    if(loop.isPaused()){
                        reset();
                        (new Thread(loop)).start();
                    }
                }
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