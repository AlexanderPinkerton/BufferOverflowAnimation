import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * Author: Alexander Pinkerton
 * Group: 9
 * BUFFER OVERFLOW SIMULATION
 */


public class Address {

    int x, y, width, height;
    Color c;
    String text = "0x000000";


    public Address(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        c = Color.BLACK;
    }

    public void draw(GraphicsContext g){
        g.setStroke(c);
        g.strokeRect(x,y,width,height);
        g.strokeText(
                text,
                x + width/2 - (text.length()*3),
                y + height/2);
    }


    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
