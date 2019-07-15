package managers;

import models.Piece;

import java.io.*;

import static constants.Constants.BOARD_NUMBER_OF_PIECES;
import static constants.Constants.REGEX;

public class FileManager {
    private static final FileManager instance = new FileManager();

    private BufferedReader reader;
    private BufferedWriter writer;

    private FileManager() {
    }

    public static FileManager getInstance() {
        return instance;
    }

    public void saveGame(String filename, Piece[][] pieces) {
        try {
            StringBuilder builder = new StringBuilder();
            for (Piece[] piece : pieces) {
                for (int j = 0; j < pieces.length; j++) {
                    builder.append(piece[j]);
                    if (j < pieces.length - 1)
                        builder.append(REGEX);
                }
                builder.append("\n");
            }
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Piece[][] loadGame(String filename) {
        Piece[][] pieces = new Piece[BOARD_NUMBER_OF_PIECES][BOARD_NUMBER_OF_PIECES];

        for (int i = 0; i < BOARD_NUMBER_OF_PIECES; i++) {
            for (int j = 0; j < BOARD_NUMBER_OF_PIECES; j++) {
                pieces[i][j] = new Piece(i, j);
            }
        }

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {

                String[] elements = line.split(REGEX);
                int col = 0;
                for (String pieceFromFile : elements) {
                    pieces[row][col].setColor(Integer.parseInt(pieceFromFile));

                    col++;
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return pieces;
    }

}
