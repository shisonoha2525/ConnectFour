import java.util.ArrayList;
import java.util.Random;

import javax.management.relation.Role;

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
    static final int COLUMN = 6;
    static final int ROW = 7;

    private int[][] board;
    private ArrayList<Record> records;
    ArrayList<Location> line;

    public static int color;
    public static int winner = -1;
    public static int pieces;

    Board() {
        records = new ArrayList<Record>();
        Random r = new Random();
        color = r.nextInt(2);
        init();
    }

    private void init() {
        winner = -1;
        pieces = 0;
        line = new ArrayList<Location>();
        board = new int[COLUMN][ROW];
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

    public int check(int turn) {
        // 横 -> 縦 -> 斜め
        for (int x = 0; x < board[0].length - 3; x++) {
            for (int y = board.length - 1; y >= 0; y--) {
                if (board[y][x] == turn && board[y][x + 1] == turn && board[y][x + 2] == turn
                        && board[y][x + 3] == turn) {
                    for (int i = 0; i < 4; i++) {
                        line.add(new Location(x + i, y));
                    }
                    return turn;
                }
            }
        }
        for (int y = board.length - 1; y >= 3; y--) {
            for (int x = 0; x < board[0].length; x++) {
                if (board[y][x] == turn && board[y - 1][x] == turn && board[y - 2][x] == turn
                        && board[y - 3][x] == turn) {
                    for (int j = 0; j < 4; j++) {
                        line.add(new Location(x, y - j));
                    }
                    return turn;
                }
            }
        }
        for (int y = board.length - 1; y >= 3; y--) {
            for (int x = 0; x < board[0].length - 3; x++) {
                if (board[y][x] == turn && board[y - 1][x + 1] == turn && board[y - 2][x + 2] == turn
                        && board[y - 3][x + 3] == turn) {
                    for (int i = 0; i < 4; i++) {
                        line.add(new Location(x + i, y - i));
                    }
                    return turn;
                }
            }
        }
        for (int y = 0; y < board.length - 3; y++) {
            for (int x = 0; x < board[0].length - 3; x++) {
                if (board[y][x] == turn && board[y + 1][x + 1] == turn && board[y + 2][x + 2] == turn
                        && board[y + 3][x + 3] == turn) {
                    for (int i = 0; i < 4; i++) {
                        line.add(new Location(x + i, y + i));
                    }
                    return turn;
                }
            }
        }
        return -1;
    }

    public void put(int x, int turn) {
        if (pieces != ROW * COLUMN && winner == -1) {
            for (int y = board.length - 1; y >= 0; y--) {
                if (board[y][x] == -1) {
                    board[y][x] = turn;
                    records.add(new Record(turn, new Location(x, y)));
                    pieces++;
                    winner = check(turn);
                    if (winner == -1) {
                        color = changeTurn(turn);
                    }
                    break;
                }
            }
        }
    }

    public void put(Record r) {
        if (r != null) {
            board[r.place.getY()][r.place.getX()] = r.turn;
            pieces++;
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
            color = records.remove(records.size() - 1).turn;
            pieces--;
            winner = -1;
            init();
            for (int i = 0; i < records.size(); i++) {
                put(records.get(i));
            }
        }
    }
}
