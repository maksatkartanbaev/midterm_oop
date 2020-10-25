package sample.heart;

import javafx.scene.canvas.GraphicsContext;
import sample.gui.Painter;

public class GameLoop implements Runnable {
    private final Grid grid;
    private final GraphicsContext context;
    private int frameRate;
    private float interval;
    private boolean running;
    private boolean paused;
    private boolean keyIsPressed;

    public GameLoop(final Grid grid, final GraphicsContext context) {
        this.grid = grid;
        this.context = context;
        frameRate = 15;
        interval = 1000.0f / frameRate;
        running = true;
        paused = false;
        keyIsPressed = false;
    }

    @Override
    public void run() {
        while (running && !paused) {
            float time = System.currentTimeMillis();

            keyIsPressed = false;
            grid.update();
            Painter.paint(grid, context);

            if(!grid.getSnake().isSafe()){
                pause();
                Painter.paintResetMessage(context);
            }

            time = System.currentTimeMillis() - time;

            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                }
            }
        }
    }

    public boolean isKeyPressed() {
        return keyIsPressed;
    }

    void pause(){
        paused = true;
    }
    public boolean isPaused(){
        return paused;
    }
}
