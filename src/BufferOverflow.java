import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Author: Alexander Pinkerton
 * Group: 9
 * BUFFER OVERFLOW SIMULATION
 */



public class BufferOverflow extends Application {

    Address[] addArray;
    GraphicsContext gc;
    int addressCount = 10;
    int width = 500;
    int height = 800;
    int stepCount = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        addArray = new Address[addressCount];
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();

        for (int i = 0; i < addressCount; i++) {
            addArray[i] = new Address(
                    0,
                    i * this.getHeight() / addressCount,
                    this.getWidth(),
                    this.getHeight() / addressCount
            );
            addArray[i].setC(Color.BLUE);
            addArray[i].setText("Local Stack Variable Space");
        }
        addArray[0].setText("ESP - Extended Stack Pointer");
        addArray[addressCount - 2].setText("ESB - Extended Base Pointer");
        addArray[addressCount - 1].setText("RETURN ADDRESS");
        drawShapes(gc);
        root.getChildren().add(canvas);
        Scene derp = new Scene(root);
        derp.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                switch (event.getCode()) {
                    case SPACE:
                        stepCount++;
                        step(stepCount);
                        break;
                }
            }
        });
        primaryStage.setScene(derp);
        primaryStage.show();
    }


    private void step(int stepCount) {
        if (stepCount < addressCount) {
            overflow();
        } else if (stepCount == addressCount) {
            clear(gc);
            addArray[addressCount - 4].setText("Insert ShellCode Here");
            addArray[addressCount - 5].setText("Insert ShellCode Here");
            addArray[addressCount - 6].setText("Insert ShellCode Here");
            addArray[addressCount - 7].setText("Insert ShellCode Here");
            addArray[addressCount - 1].setText("Change to Address of Shellcode");
            drawShapes(gc);
        }
    }


    private void overflow() {
        clear(gc);
        for (int i = 1; i < stepCount; i++) {
            addArray[i].setC(Color.RED);
            char c = (char) (64 + i);
            addArray[i].setText(c + "" + c + "" + c + "" + c);
        }
        addArray[addressCount - 1].setText("RETURN ADDRESS");
        drawShapes(gc);
    }


    private void clear(GraphicsContext gc) {
        gc.clearRect(0, 0, width, height);
    }

    private void drawShapes(GraphicsContext gc) {
        for (int i = 0; i < addressCount; i++) {
            addArray[i].draw(gc);
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}