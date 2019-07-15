package controllers;

import managers.FileManager;
import models.Board;
import models.NullPlayer;
import models.Piece;
import views.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static constants.Constants.*;

public class BoardController {
    private Board board;
    private BoardView boardView;

    public BoardController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;

        boardView.addLoadGameAction(new LoadGameAction());
        boardView.addSaveGameAction(new SaveGameAction());
        boardView.addNewGameAction(new NewGameAction());
      //  boardView.addMouseListener(new BoardMouseListener());
    }

    public class NewGameAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < BOARD_NUMBER_OF_PIECES; i++) {
                for (int j = 0; j < BOARD_NUMBER_OF_PIECES; j++) {
                    board.setPieceColor(new NullPlayer(BOARD_EMPTY_FIELD), i, j);
                }
            }

            board.setPlayer1Move(true);
            //TODO implement later
            // setOutput();
            boardView.repaint();

        }
    }

    public class SaveGameAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FileManager.getInstance().saveGame(FILENAME, board.getPieces());
        }
    }

    public class LoadGameAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Piece[][] piecesFromFile = FileManager.getInstance().loadGame(FILENAME);
            board.setPieces(piecesFromFile);

            int numberofpieces = 0;

            for (int i = 0; i < BOARD_NUMBER_OF_PIECES; i++) {
                for (int j = 0; j < BOARD_NUMBER_OF_PIECES; j++) {
                    if (board.getPiece(i, j).getColor() != BOARD_EMPTY_FIELD) {
                        numberofpieces++;
                    }
                }
            }

            if (numberofpieces % 2 == 0) {
                board.setPlayer1Move(true);
            } else {
                board.setPlayer1Move(false);
            }

            boardView.repaint();
        }
    }
/*
    public class BoardMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!(isWinner(p1)) && !(isWinner(p2))) {
                if (e.getX() <= BOARD_GAME_AREA && e.getY() <= BOARD_GAME_AREA && isValid(e.getY() / BOARD_PIECE_SIZE, e.getX() / BOARD_PIECE_SIZE)) {
                    if (p1turn == true) {
                        setPieceColor(p1, (e.getY()) / BOARD_PIECE_SIZE, (e.getX()) / BOARD_PIECE_SIZE);
                        repaint();
                        printBoard();
                        setp1turn(false);
                    } else {
                        setPieceColor(p2, (e.getY()) / BOARD_PIECE_SIZE, (e.getX()) / BOARD_PIECE_SIZE);
                        boardView.repaint();
                        printBoard();
                        setp1turn(true);
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    */

}
