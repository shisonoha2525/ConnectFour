import processing.core.PApplet;

public class ConnectFour extends PApplet {
    Board board;
    static final int SCREEN_SIZE = 500;
    static final float MARGIN = SCREEN_SIZE / 10;
    static final float PIECE_SIZE = 50.0f;
    static final float PIECE_DIST = 20;

    private boolean mouseClickedFlag;
    private boolean gameOverFlag;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        board = new Board();
        gameOverFlag = false;
    }

    private void drawPiece(int x, int y, int color, int stroke) {
        // color == -1 : 空き
        // color == 0  : プレイヤー(赤)
        // color == 1  : コンピュータ(黄色)
        switch (color) {
        case -1:
            fill(0xff, 0xff, 0xff);
            break;
        case 0:
            fill(0xff, 0x00, 0x00);
            break;
        case 1:
            fill(0xff, 0xff, 0x00);
            break;
        }
        strokeWeight(stroke);
        ellipse((x + 1) * (PIECE_DIST + PIECE_SIZE) - 3 * PIECE_SIZE / 5,
                MARGIN / 2 + (y + 1) * (PIECE_DIST + PIECE_SIZE) + PIECE_SIZE / 5, PIECE_SIZE, PIECE_SIZE);

    }

    public void draw() {
        background(255);
        strokeWeight(1);
        fill(0xdd, 0xdd, 0xdd);
        rect(0, 60, 500, 450);
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 7; x++) {
                drawPiece(x, y, board.get(x, y), 1);
            }
        }
        if (Board.winner == -1) {
            for (int i = 0; i < 7; i++) {
                if (board.get(i, 0) == -1) {
                    beginShape(TRIANGLES);
                    // vertex((i + 1) * (PIECE_DIST + PIECE_SIZE) - 3 * PIECE_SIZE / 5, MARGIN / 2 + PIECE_SIZE / 5, PIECE_SIZE,
                    //         PIECE_SIZE);
                    fill(0x00, 0x00, 0xff);
                    vertex((i + 1) * (PIECE_DIST + PIECE_SIZE) - 9 * PIECE_SIZE / 10,
                            MARGIN * 0.8f - 3 * PIECE_SIZE / 10);
                    vertex((i + 1) * (PIECE_DIST + PIECE_SIZE) - 3 * PIECE_SIZE / 10,
                            MARGIN * 0.8f - 3 * PIECE_SIZE / 10);
                    vertex((i + 1) * (PIECE_DIST + PIECE_SIZE) - 3 * PIECE_SIZE / 5,
                            (MARGIN * 0.8f + 3 * PIECE_SIZE / 10) * (float) Math.sqrt(3) / 2);
                    endShape();
                }
            }
            if (board.get(positionX(mouseX), 0) == -1) {
                drawPiece(positionX(mouseX), -1, Board.color, 1);
            }
        } else {
            for (int i = 0; i < board.line.size(); i++) {
                drawPiece(board.line.get(i).getX(), board.line.get(i).getY(), Board.color, 5);
            }
        }
    }

    public void mouseClicked() {
        mouseClickedFlag = true;
        board.put(positionX(mouseX), Board.color);
    }

    public void keyPressed() {
        if (key == 's') {

        } else if (key == 'r') {
            setup();
        } else if (key == 'u') {
            board.undo();
        }
    }

    private int positionX(int x) {
        // System.out.println(7 * x / SCREEN_SIZE);
        return 7 * x / SCREEN_SIZE;
    }

    public static void main(String[] args) {
        PApplet.main(ConnectFour.class.getName());
    }
}
