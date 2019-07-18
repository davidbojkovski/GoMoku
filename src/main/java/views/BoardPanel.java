package views;

import models.Piece;

import javax.swing.*;
import java.awt.*;

import static constants.Constants.*;

public class BoardPanel extends JPanel {
    private Piece[][] pieces;

    public BoardPanel(Piece[][] pieces) {
        this.pieces = pieces;
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i <= BOARD_GAME_AREA; i += 40) {
            g.setColor(Color.BLACK);
            g.drawLine(i, 0, i, BOARD_GAME_AREA);
            g.drawLine(0, i, BOARD_GAME_AREA, i);
        }

        for (int i = 0; i < BOARD_NUMBER_OF_PIECES; i++) {
            for (int j = 0; j < BOARD_NUMBER_OF_PIECES; j++) {
                if (pieces[i][j].getColor() == 1) {
                    g.setColor(Color.BLUE);
                    g.fillOval(j * BOARD_PIECE_SIZE, i * BOARD_PIECE_SIZE, BOARD_PIECE_SIZE - 1, BOARD_PIECE_SIZE - 1);
                } else if (pieces[i][j].getColor() == 2) {
                    g.setColor(Color.RED);
                    g.fillOval(j * BOARD_PIECE_SIZE, i * BOARD_PIECE_SIZE, BOARD_PIECE_SIZE - 1, BOARD_PIECE_SIZE - 1);
                }
            }
        }

    }
}
