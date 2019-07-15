package models;

import managers.FileManager;

import static constants.Constants.*;

public class Board {
    private Piece[][] pieces;
    private Player p1;
    private Player p2;
    private boolean player1Move = true;

    public Board() {
        pieces = new Piece[BOARD_NUMBER_OF_PIECES][BOARD_NUMBER_OF_PIECES]; // 15x15 board
        p1 = new Player(1);
        p2 = new Player(2);

        for (int i = 0; i < BOARD_NUMBER_OF_PIECES; i++) {
            for (int j = 0; j < BOARD_NUMBER_OF_PIECES; j++) {
                pieces[i][j] = new Piece(i, j);
            }
        }

    }


    public Piece[][] getPieces() {
        return pieces;
    }

    public Piece getPiece(int row, int column){
        return pieces[row][column];
    }

    public void setPieces(Piece[][] _pieces){
        for (int i = 0; i < BOARD_NUMBER_OF_PIECES; i++) {
            for (int j = 0; j < BOARD_NUMBER_OF_PIECES; j++) {
                this.pieces[i][j] = _pieces[i][j];
            }
        }
    }

    // adds a piece on the board
    public void setPieceColor(Player p, int row, int column) {
        pieces[row][column].setColor(p.getColor());
    }

    // checks if the field is empty
    public boolean isValid(int row, int column) {
        if (pieces[row][column].getColor() == BOARD_EMPTY_FIELD)
            return true;
        else
            return false;
    }

    // changes the turn
    public void setPlayer1Move(boolean b) {
        player1Move = b;
    }

    public boolean getPlayer1Move(){
        return player1Move;
    }

    public int checkvertical(Player p, int row, int column) {
        int samecolor = 0;
        /* check until 10, not to step out of bounds of the board */
        if (row <= 10) {
            if (pieces[row][column].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 1][column].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 2][column].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 3][column].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 4][column].getColor() == p.getColor()) {
                samecolor += 1;
            }
        }
        return samecolor;
    }

    public int checkhorisontal(Player p, int row, int column) {
        int samecolor = 0;

        /* check until 10, not to step out of bounds of the board */
        if (column <= 10) {
            if (pieces[row][column].getColor() == p.getColor()) {
                samecolor += 1;
            }

            if (pieces[row][column + 1].getColor() == p.getColor()) {
                samecolor += 1;
            }

            if (pieces[row][column + 2].getColor() == p.getColor()) {
                samecolor += 1;
            }

            if (pieces[row][column + 3].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row][column + 4].getColor() == p.getColor()) {
                samecolor += 1;
            }
        }

        return samecolor;
    }

    public int checkrightdiagonal(Player p, int row, int column) {
        int samecolor = 0;

        /* check until 10, not to step out of bounds of the board */
        if (row <= 10 && column <= 10) {
            if (pieces[row][column].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 1][column + 1].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 2][column + 2].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 3][column + 3].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 4][column + 4].getColor() == p.getColor()) {
                samecolor += 1;
            }
        }

        return samecolor;
    }

    public int checkleftdiagonal(Player p, int row, int column) {
        int samecolor = 0;

        /* check until 10, not to step out of bounds of the board */
        if (row <= 10 && column >= 4) {
            if (pieces[row][column].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 1][column - 1].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 2][column - 2].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 3][column - 3].getColor() == p.getColor()) {
                samecolor += 1;
            }
            if (pieces[row + 4][column - 4].getColor() == p.getColor()) {
                samecolor += 1;
            }
        }

        return samecolor;
    }

    public boolean isWinner(Player p) {
        for (int i = 0; i < BOARD_NUMBER_OF_PIECES; i++) {
            for (int j = 0; j < BOARD_NUMBER_OF_PIECES; j++) {

                int ver = checkvertical(p, i, j);
                int hor = checkhorisontal(p, i, j);
                int ld = checkleftdiagonal(p, i, j);
                int rd = checkrightdiagonal(p, i, j);

                if (hor == 5 || ver == 5 || ld == 5 || rd == 5)
                    return true;
            }
        }
        return false;
    }

    public boolean isDraw() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (isValid(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

   /* public void runGame() {
        while (!(isWinner(p1)) && !(isWinner(p2))) {
            setOutput();

            if (isDraw()) {
                text.setText("It's a draw");
                break;
            }

        }

        if (isWinner(p1))
            text.setText(PLAYER_1_NAME + " winner");
        else
            text.setText(PLAYER_2_NAME + " winner");
    }

    public void setOutput() {
        if (player1Move)
            text.setText(PLAYER_1_NAME + "'s turn");
        else
            text.setText(PLAYER_2_NAME + "'s turn");
    }
*/
    public void printBoard() {
        for (int i = 0; i < BOARD_NUMBER_OF_PIECES; i++) {
            for (int j = 0; j < BOARD_NUMBER_OF_PIECES; j++) {
                System.out.print(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
