package sample.gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.heart.Food;
import sample.heart.Grid;
import sample.heart.Point;
import sample.heart.Snake;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static sample.heart.Grid.SIZE;

public class Painter {

    public static void paint(Grid grid, GraphicsContext gc){
        gc.setFill(Grid.COLOR);
        gc.fillRect(0, 0, grid.getWidth(), grid.getHeight());

        gc.setFill(Food.COLOR);
        paintPoint(grid.getFood().getPoint(), gc);

        Snake snake = grid.getSnake();
        gc.setFill(Snake.COLOR);
        snake.getPoints().forEach(point -> paintPoint(point, gc));

        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + snake.getPoints().size(), 10, 10);

    }

    private static void paintPoint(Point point, GraphicsContext gc){
        gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
    }

    public static void paintResetMessage(Grid grid, GraphicsContext gc){
        gc.setFill(Color.AQUAMARINE);
        int highscore = grid.getSnake().getPoints().size();
        System.out.println(highscore);
        try {
            File myObj = new File("Highscore.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){
                int test = Integer.parseInt(myReader.nextLine());
                if(test < highscore){
                    gc.fillText("New highscore!", 150, 200);
                    try{
                        FileWriter myWriter = new FileWriter("Highscore.txt");
                        myWriter.write(Integer.toString(highscore));
                        myWriter.close();
                    } catch (IOException e){
                        System.out.println("Error");
                        e.printStackTrace();
                    }
                } else{
                    highscore = test;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("Error");
            e.printStackTrace();
        }
        gc.fillText("High score: " + highscore, 150, 225);
        gc.fillText("Hit RETURN to start again.", 150,250);
    }
}
