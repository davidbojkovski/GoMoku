package game;

import models.Piece;

import static constants.Constants.*;
import static constants.Constants.BOARD_NUMBER_OF_PIECES;

public class TextOutput {
    private static final TextOutput instance = new TextOutput();

    private TextOutput() {
    }

    public static TextOutput getInstance() {
        return instance;
    }

    public String moveOutput(boolean player1Move) {
        return player1Move ? PLAYER_1_TURN : PLAYER_2_TURN;
    }

    public void printBoardToConsole(Piece[][] pieces) {
        for (int i = 0; i < BOARD_NUMBER_OF_PIECES; i++) {
            for (int j = 0; j < BOARD_NUMBER_OF_PIECES; j++) {
                System.out.print(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
