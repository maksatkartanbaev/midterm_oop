package sample.gui;

import javafx.scene.canvas.GraphicsContext;
import sample.heart.Food;
import sample.heart.Grid;
import sample.heart.Point;
import sample.heart.Snake;

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

    }

    private static void paintPoint(Point point, GraphicsContext gc){
        gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
    }

}
