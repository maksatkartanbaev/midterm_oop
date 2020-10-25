package sample.heart;

import javafx.scene.paint.Color;

import java.util.Random;

public class Grid{

    public static final int SIZE = 20;
    public static final Color COLOR = Color.BLACK;
    private final int columns;
    private final int rows;

    private Snake snake;
    private Food food;

    public Grid(final double width, final double height){

        rows = (int) width / SIZE;
        columns = (int) height / SIZE;

        snake = new Snake(this, new Point(rows / 2, columns / 2));

        food = new Food(getRandomPoint());
    }

    public Point wrap(Point point){
        int x = point.getX();
        int y = point.getY();
        if (x >= rows) x = 0;
        if (y >= columns) y = 0;
        if (x < 0) x = rows - 1;
        if (y < 0) y = columns - 1;
        return new Point(x, y);
    }

    public void update(){
        if(food.getPoint().equals(snake.getHead())){
            food.setPoint(getRandomPoint());
        }
        snake.move();
    }

    private Point getRandomPoint(){
        Random random = new Random();
        Point point;
        do{
            point = new Point(random.nextInt(rows),random.nextInt(columns));
        }while (point.equals(snake.getHead()));
        return point;
    }

    public double getWidth(){
        return rows * SIZE;
    }

    public double getHeight(){
        return columns * SIZE;
    }

    public Snake getSnake(){
        return snake;
    }

    public Food getFood(){
        return food;
    }

}