import controllers.BoardController;
import game.GameValidation;
import game.TextOutput;
import models.Board;
import views.BoardView;

import static constants.Constants.PLAYER_1_NAME;
import static constants.Constants.PLAYER_2_NAME;

public class GoMoku {

    private static void runGame(Board board, BoardView boardView){
        while (!(GameValidation.getInstance().isWinner(board.getPieces(), board.getPlayer1())) &&
                !(GameValidation.getInstance().isWinner(board.getPieces(), board.getPlayer2()))) {

            boardView.setTextFieldOutput(TextOutput.getInstance().moveOutput(board.getPlayer1Move()));

            if (GameValidation.getInstance().isDraw(board)) {
                boardView.setTextFieldOutput("It's a draw");
                break;
            }

        }

        if (GameValidation.getInstance().isWinner(board.getPieces(), board.getPlayer1()))
            boardView.setTextFieldOutput(PLAYER_1_NAME + " winner");
        else
            boardView.setTextFieldOutput(PLAYER_2_NAME + " winner");

    }

    public static void main(String[] args) {
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        BoardController boardController = new BoardController(board, boardView);

        runGame(board, boardView);
    }
}
