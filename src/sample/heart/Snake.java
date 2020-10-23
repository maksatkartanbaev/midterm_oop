package sample.heart;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Snake {

    public static final Color COLOR = Color.WHITE;
    private int length;
    private int xVelocity;
    private int yVelocity;
    private Grid grid;
    private List<Point> points;
    private Point head;

    public Snake(Grid grid, Point initialPoint){

        length = 1;
        points = new LinkedList<>();
        points.add(initialPoint);
        head = initialPoint;
        this.grid = grid;
        xVelocity = 0;
        yVelocity = 0;

    }


    private void shiftTo(Point point){
        checkAndAdd(point);
        points.remove(0);
    }

    private void checkAndAdd(Point point){
        point = grid.wrap(point);
        points.add(point);
        head = point;
    }

    public List<Point> getPoints(){
        return points;
    }

    private boolean isStill(){
        return xVelocity == 0 & yVelocity == 0;
    }

    public void move(){
        if(!isStill()){
            shiftTo(head.translate(xVelocity, yVelocity));
        }
    }

    public void setUp(){
        if(yVelocity == 1 && length > 1) return;
        xVelocity = 0;
        yVelocity = -1;
    }

    public void setDown(){
        if(yVelocity == -1 && length > 1) return;
        xVelocity = 0;
        yVelocity = 1;
    }

    public void setLeft(){
        if(xVelocity == 1 && length > 1) return;
        xVelocity = -1;
        yVelocity = 0;
    }

    public void setRight(){
        if(xVelocity == -1 && length > 1) return;
        xVelocity = 1;
        yVelocity = 0;
    }

}
