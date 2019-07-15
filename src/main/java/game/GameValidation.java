package game;

import models.Board;
import models.Piece;
import models.Player;

import static constants.Constants.BOARD_NUMBER_OF_PIECES;

public class GameValidation {
    private static final GameValidation instance = new GameValidation();

    public static GameValidation getInstance(){
        return instance;
    }

    public boolean isDraw(Board board) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board.isValid(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isWinner(Piece[][] pieces, Player player) {
        for (int i = 0; i < BOARD_NUMBER_OF_PIECES; i++) {
            for (int j = 0; j < BOARD_NUMBER_OF_PIECES; j++) {

                int ver = checkVertical(pieces, player, i, j);
                int hor = checkHorizontal(pieces, player, i, j);
                int ld = checkLeftDiagonal(pieces, player, i, j);
                int rd = checkRightDiagonal(pieces, player, i, j);

                if (hor == 5 || ver == 5 || ld == 5 || rd == 5)
                    return true;
            }
        }
        return false;
    }

    public int checkVertical(Piece[][] pieces, Player player, int row, int column) {
        int samecolor = 0;
        /* check until 10, not to step out of bounds of the board */
        if (row <= 10) {
            if (pieces[row][column].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 1][column].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 2][column].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 3][column].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 4][column].getColor() == player.getColor()) {
                samecolor += 1;
            }
        }
        return samecolor;
    }

    public int checkHorizontal(Piece[][] pieces, Player player, int row, int column) {
        int samecolor = 0;

        /* check until 10, not to step out of bounds of the board */
        if (column <= 10) {
            if (pieces[row][column].getColor() == player.getColor()) {
                samecolor += 1;
            }

            if (pieces[row][column + 1].getColor() == player.getColor()) {
                samecolor += 1;
            }

            if (pieces[row][column + 2].getColor() == player.getColor()) {
                samecolor += 1;
            }

            if (pieces[row][column + 3].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row][column + 4].getColor() == player.getColor()) {
                samecolor += 1;
            }
        }

        return samecolor;
    }

    public int checkRightDiagonal(Piece[][] pieces, Player player, int row, int column) {
        int samecolor = 0;

        /* check until 10, not to step out of bounds of the board */
        if (row <= 10 && column <= 10) {
            if (pieces[row][column].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 1][column + 1].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 2][column + 2].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 3][column + 3].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 4][column + 4].getColor() == player.getColor()) {
                samecolor += 1;
            }
        }

        return samecolor;
    }

    public int checkLeftDiagonal(Piece[][] pieces, Player player, int row, int column) {
        int samecolor = 0;

        /* check until 10, not to step out of bounds of the board */
        if (row <= 10 && column >= 4) {
            if (pieces[row][column].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 1][column - 1].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 2][column - 2].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 3][column - 3].getColor() == player.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 4][column - 4].getColor() == player.getColor()) {
                samecolor += 1;
            }
        }

        return samecolor;
    }

}
