import java.util.ArrayList;
import java.util.Random;

class Location {
    private int x;
    private int y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Record {
    int turn;
    Location place;

    Record(int turn, Location place) {
        this.turn = turn;
        this.place = place;
    }
}

public class Board {
    private int[][] board;
    private ArrayList<Record> records;
    public static int color;

    Board() {
        records = new ArrayList<Record>();
        Random r = new Random();
        color = r.nextInt(2);
        init();
    }

    private void init() {
        board = new int[6][7];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                board[y][x] = -1;
                // System.out.println("(" + x + ", " + y + ")");
            }
        }
    }

    public static int changeTurn(int turn) {
        return turn == 0 ? 1 : (turn == 1 ? 0 : -1);
    }

    public void put(int x, int turn) {
        for (int y = board.length - 1; y >= 0; y--) {
            if (board[y][x] == -1) {
                board[y][x] = turn;
                records.add(new Record(turn, new Location(x, y)));
                color = changeTurn(turn);
                break;
            }
        }
    }

    public void put(Record r) {
        if (r != null) {
            board[r.place.getY()][r.place.getX()] = r.turn;
        }
    }

    public int get(int x, int y) {
        return board[y][x];
    }

    public int get(Location location) {
        return board[location.getY()][location.getX()];
    }

    public void undo() {
        if (records.size() > 0) {
            records.remove(records.size() - 1);
            init();
            for (int i = 0; i < records.size(); i++) {
                put(records.get(i));
            }
        }
    }
}
