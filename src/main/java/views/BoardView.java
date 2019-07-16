package views;

import models.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import static constants.Constants.*;

public class BoardView extends JFrame {

    private Board board;
    private JMenuItem saveMenuItem = new JMenuItem(J_MENU_ITEM_SAVE);
    private JMenuItem loadMenuItem = new JMenuItem(J_MENU_ITEM_LOAD);
    private JMenuItem newGameMenuItem = new JMenuItem(J_MENU_ITEM_NEW_GAME);
    private JPanel boardPanel;
    private JTextField textField;

    public BoardView(Board board) {
        this.board = board;

        this.setLayout(new BorderLayout());
        this.setTitle(GOMOKU_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(BOARD_VIEW_WIDTH, BOARD_VIEW_HEIGHT);


        JMenu fileMenu = new JMenu(J_MENU_FILE);
        fileMenu.add(newGameMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        boardPanel = new BoardPanel(board.getPieces());
        this.add(boardPanel, BorderLayout.CENTER);

        JPanel textPanel = new JPanel();
        textField = new JTextField(J_TEXT_FIELD_NUMBER_OF_COLUMNS);
        textField.setEditable(false);
        textPanel.add(textField);
        this.add(textPanel, BorderLayout.PAGE_END);

        this.setVisible(true);
        this.setResizable(false);
    }

    public void addSaveGameAction(ActionListener saveGameListener) {
        saveMenuItem.addActionListener(saveGameListener);
    }

    public void addLoadGameAction(ActionListener loadGameListener) {
        loadMenuItem.addActionListener(loadGameListener);
    }

    public void addNewGameAction(ActionListener newGameListener) {
        newGameMenuItem.addActionListener(newGameListener);
    }

    public void addMouseListener(MouseListener mouseListener){
        boardPanel.addMouseListener(mouseListener);
    }

    public void setTextFieldOutput(String text){
        textField.setText(text);
    }
}
