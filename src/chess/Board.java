package chess;

import chess.pieces.*;

public class Board {
    Space[][] spaces = new Space[8][8];

    public void resetBoard(){
        //Set Major characters
        //White
        for (int wm = 0; wm < 8; wm++) {
            if(wm == 0 || wm == 7){
                spaces[0][wm] = new Space(0, wm, new Rook(true));
            } else if (wm == 1 || wm == 6) {
                spaces[0][wm] = new Space(0, wm, new Knight(true));
            } else if(wm == 2 || wm == 5){
                spaces[0][wm] = new Space(0, wm, new Bishop(true));
            } else if(wm == 4){
                spaces[0][wm] = new Space(0, wm, new Queen(true));
            }
            else {
                spaces[0][wm] = new Space(0, wm, null);
            }
        }
        //Black
        for (int bm = 0; bm < 8; bm++) {
            if(bm == 0 || bm == 7){
                spaces[7][bm] = new Space(7, bm, new Rook(false));
            } else if(bm == 1 || bm == 6) {
                spaces[7][bm] = new Space(7, bm, new Knight(false));
            } else if(bm == 2 || bm == 5){
                spaces[7][bm] = new Space(7, bm, new Bishop(false));
            } else if(bm == 4){
                spaces[7][bm] = new Space(7, bm, new Queen(false));
            }
            else {
                spaces[7][bm] = new Space(7, bm, null);
            }
        }

        //Set Pawns
        //White side
        for (int w = 0; w < 8; w++) {
            spaces[1][w] = new Space(1, w, new Pawn(true));
        }
        //Black side
        for (int b = 0; b < 8; b++) {
            spaces[6][b] = new Space(6, b, new Pawn(false));
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

    public Piece getPiece(int x, int y) {

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Index out of bounds");
            return null;
        }

        return spaces[x][y].getPiece();
    }

    @Override
    public String toString(){
        String boardDisplay = new String();
        //Building out spaces and numbering the X-axis
        boardDisplay += "---------------------------------\n";
        for (int sx = 0; sx < 8; sx++) {
            boardDisplay += sx;
            for (int sy = 0; sy < 8; sy++) {
                boardDisplay += spaces[sx][sy].toString();
            }
            boardDisplay += "\n";
        }
        boardDisplay += " --------------------------------\n ";
        for (int dy = 0; dy < 8; dy++){
            boardDisplay += "| " + dy + "|";
        }
        boardDisplay += "\n---------------------------------\n";

        return boardDisplay;
    }
}
