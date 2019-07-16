package models;

import static constants.Constants.*;

public class Board {
    private Piece[][] pieces;
    private Player player1;
    private Player player2;
    private boolean player1Move;

    public Board() {
        pieces = new Piece[BOARD_NUMBER_OF_PIECES][BOARD_NUMBER_OF_PIECES]; // 15x15 board
        player1 = new Player(1);
        player2 = new Player(2);
        player1Move = true;

        for (int i = 0; i < BOARD_NUMBER_OF_PIECES; i++) {
            for (int j = 0; j < BOARD_NUMBER_OF_PIECES; j++) {
                pieces[i][j] = new Piece(i, j);
            }
        }

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
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
        return pieces[row][column].getColor() == BOARD_EMPTY_FIELD;
    }

    // changes the turn
    public void setPlayer1Move(boolean b) {
        player1Move = b;
    }

    public boolean getPlayer1Move(){
        return player1Move;
    }
}
