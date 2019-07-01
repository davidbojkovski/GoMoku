import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import javax.swing.*;

public class Board extends JFrame {
    private Piece pieces[][];
    private Player p1;
    private Player p2;
    private boolean p1turn = true;
    private JTextField text;
    private static final String player1 = "Player 1";
    private static final String player2 = "Player 2";


    Board() {
        pieces = new Piece[15][15]; // 15x15 board
        p1 = new Player(1);
        p2 = new Player(2);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                pieces[i][j] = new Piece(i, j);
            }
        }

        makeBoard();
    }

    public void makeBoard() {
        //this.setLayout(new GridLayout(14,14,0,0));
        this.setLayout(new BorderLayout());
        this.setTitle("GoMoku");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(620, 690);


        JMenuItem mi1 = new JMenuItem("Save");
        mi1.addActionListener(new SaveGameAction());
        JMenuItem mi2 = new JMenuItem("Load");
        mi2.addActionListener(new LoadGameAction());
        JMenuItem mi3 = new JMenuItem("New Game");
        mi3.addActionListener(new NewGameAction());
        JMenu m1 = new JMenu("File");
        m1.add(mi3);
        m1.add(mi1);
        m1.add(mi2);
        JMenuBar bar = new JMenuBar();
        bar.add(m1);
        this.setJMenuBar(bar);

        JPanel boardpanel = new DrawLines();
        boardpanel.addMouseListener(new MyMouseListener());
        //boardpanel.add(new drawCircles());
        this.add(boardpanel, BorderLayout.CENTER);

        JPanel textpanel = new JPanel();
        text = new JTextField(30);
        text.setEditable(false);
        textpanel.add(text);
        this.add(textpanel, BorderLayout.PAGE_END);

        this.setVisible(true);
        this.setResizable(false);
    }

    public class DrawLines extends JPanel {
        public DrawLines() {
        }

        public void paintComponent(Graphics g) {
            for (int i = 0; i <= 600; i += 40) {
                g.setColor(Color.BLACK);
                g.drawLine(i, 0, i, 600);
                g.drawLine(0, i, 600, i);
            }

            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (pieces[i][j].getColor() == 1) {
                        g.setColor(Color.BLUE);
                        g.fillOval(j * 40, i * 40, 39, 39);
                    } else if (pieces[i][j].getColor() == 2) {
                        g.setColor(Color.RED);
                        g.fillOval(j * 40, i * 40, 39, 39);
                    }
                }
            }

        }
    }

    public class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!(isWinner(p1)) && !(isWinner(p2))) {
                if (e.getX() <= 600 && e.getY() <= 600 && isValid(e.getY() / 40, e.getX() / 40)) {
                    if (p1turn == true) {
                        setPieceColor(p1, (e.getY()) / 40, (e.getX()) / 40);
                        repaint();
                        printBoard();
                        setp1turn(false);
                    } else {
                        setPieceColor(p2, (e.getY()) / 40, (e.getX()) / 40);
                        repaint();
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

    // adds a piece on the board
    public void setPieceColor(Player p, int row, int column) {
        pieces[row][column].setColor(p.getColor());
    }

    // checks if the field is empty
    public boolean isValid(int row, int column) {
        if (pieces[row][column].getColor() == 0)
            return true;
        else
            return false;
    }

    // changes the turn
    public void setp1turn(boolean b) {
        p1turn = b;
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
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {

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

    public void runGame() {
        while (!(isWinner(p1)) && !(isWinner(p2))) {
            setOutput();

            if (isDraw()) {
                text.setText("It's a draw");
                break;
            }

        }

        if (isWinner(p1))
            text.setText(player1 + " winner");
        else
            text.setText(player2 + " winner");
    }

    public void setOutput() {
        if (p1turn)
            text.setText(player1 + "'s turn");
        else
            text.setText(player2 + "'s turn");
    }

    public void printBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void newGame() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                pieces[i][j].setColor(0);
            }
        }

        setp1turn(true);
        setOutput();
        repaint();
    }

    public class NewGameAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            newGame();
        }
    }

    public void saveGame(String filename) {
        try {
            StringBuilder builder = new StringBuilder();
            for (Piece[] piece : pieces) {
                for (int j = 0; j < pieces.length; j++) {
                    builder.append(piece[j] + "");
                    if (j < pieces.length - 1)
                        builder.append(",");
                }
                builder.append("\n");//append new line at the end of the row
            }
            //builder.append(String.valueOf(p1turn)) ;
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(builder.toString());
            writer.close();
        } catch (IOException ex) {
        }
    }

    public class SaveGameAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            saveGame("Gomoku.txt");
        }

    }


    public void loadGame(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");
                int col = 0;
                for (String c : cols) {
                    pieces[row][col].setColor(Integer.parseInt(c));
                    col++;
                }
                row++;
            }
            reader.close();

            int numberofpieces = 0;

            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (pieces[i][j].getColor() != 0) {
                        numberofpieces++;
                    }
                }
            }

            if (numberofpieces % 2 == 0) {
                setp1turn(true);
            } else {
                setp1turn(false);
            }


            repaint();
        } catch (IOException ex) {
        }

    }

    public class LoadGameAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loadGame("Gomoku.txt");
        }

    }

}
