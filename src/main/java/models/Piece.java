package models;

public class Piece {
    //location
    private int row ;
    private int column ;
    private int color ;

    Piece(int row, int column)
    {
        this.row = row ;
        this.column = column ;
        color = 0 ;
    }

    public void setColor(int color)
    {
        this.color = color ;
    }

    public int getColor()
    {
        return color ;
    }

    @Override
    public String toString()
    {
        return Integer.toString(color) ;
    }
}
