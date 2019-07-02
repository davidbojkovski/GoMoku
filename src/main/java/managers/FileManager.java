package managers;

import models.Piece;

import java.io.*;

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
                    builder.append(piece[j] + "");
                    if (j < pieces.length - 1)
                        builder.append(",");
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
        Piece[][] pieces = new Piece[15][15];

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = "";
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(",");
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
