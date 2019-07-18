import controllers.BoardController;
import game.GameValidation;
import game.TextOutput;
import models.Board;
import views.BoardView;

import static constants.Constants.*;

public class GoMoku {

    private static void runGame(Board board, BoardView boardView){
        while (!(GameValidation.getInstance().isWinner(board.getPieces(), board.getPlayer1())) &&
                !(GameValidation.getInstance().isWinner(board.getPieces(), board.getPlayer2()))) {

            boardView.setTextFieldOutput(TextOutput.getInstance().moveOutput(board.getPlayer1Move()));

            if (GameValidation.getInstance().isDraw(board)) {
                boardView.setTextFieldOutput(GOMOKU_DRAW_GAME);
                break;
            }

        }

        if (GameValidation.getInstance().isWinner(board.getPieces(), board.getPlayer1()))
            boardView.setTextFieldOutput(PLAYER_1_WINNER);
        else
            boardView.setTextFieldOutput(PLAYER_2_WINNER);

    }

    public static void main(String[] args) {
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        BoardController boardController = new BoardController(board, boardView);

        runGame(board, boardView);
    }
}
