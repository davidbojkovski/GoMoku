import controllers.BoardController;
import models.Board;
import views.BoardView;

public class GoMoku {
    public static void main(String[] args) {
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        BoardController boardController = new BoardController(board, boardView);

    }
}
