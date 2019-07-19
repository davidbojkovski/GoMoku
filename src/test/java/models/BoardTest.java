package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoardTest {
    private Board board;
    private Player player1 = mock(Player.class);
    private Player player2 = mock(Player.class);

    @Before
    public void setUp(){
        board = new Board();
        when(player1.getColor()).thenReturn(1);
        when(player2.getColor()).thenReturn(2);
    }

    @Test
    public void testPlayer1Color(){
        assertEquals(board.getPlayer1().getColor(), player1.getColor());
    }

    @Test
    public void testPlayer2Color(){
        assertEquals(board.getPlayer2().getColor(), player2.getColor());
    }


}