package sample.heart;

import javafx.scene.paint.Color;

public class Grid{

    public static final int SIZE = 10;
    public static final Color COLOR = new Color(0,0,0,1);
    private final int columns;
    private final int rows;

    private final Snake snake;

    public Grid(final double width, final double height){

        rows = (int) width / SIZE;
        columns = (int) height / SIZE;

        snake = new Snake(this, new Point(rows / 2, columns / 2));

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
        snake.move();
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

}