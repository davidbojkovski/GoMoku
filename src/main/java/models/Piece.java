package models;

import static constants.Constants.BOARD_EMPTY_FIELD;

public class Piece {
    //location
    private int row;
    private int column;
    private int color;

    public Piece(int row, int column) {
        this.row = row;
        this.column = column;
        color = BOARD_EMPTY_FIELD;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return Integer.toString(color);
    }
}
