package chess;

import chess.pieces.Pawn;

public class Board {
    Space[][] spaces;

    public void resetBoard(){
        //Set Major characters

        //Set Pawns
        //White side
        for (int w = 0; w < 8; w++) {
            spaces[1][w] = new Space(1, w, new Pawn(true));
        }
        //Black side
        for (int b = 0; b < 8; b++) {
            spaces[1][b] = new Space(1, b, new Pawn(true));
        }
        //Set Empty spaces
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                spaces[i][j] = new Space(i, j, null);
            }
        }
    }

    public Space getBox(int x, int y) {

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Index out of bounds");
            return null;
        }

        return spaces[x][y];
    }
}
