import processing.core.PApplet;

public class ConnectFour extends PApplet {
    public void settings() {
        size(500, 500);
    }

    public void draw() {
        background(255);
    }

    public static void main(String[] args) {
        PApplet.main(ConnectFour.class.getName());
    }
}
