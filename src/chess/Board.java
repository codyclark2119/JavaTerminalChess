package chess;

import chess.pieces.*;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Board {
    Space[][] spaces;
    ArrayList<Piece> pieces;

    public Board(){
        this.spaces = new Space[8][8];
        this.pieces = new ArrayList<>();
    }

    public void resetBoard(){
        if (this.pieces.size() > 0 ){
            this.pieces.clear();
        }
        //Set Major characters
        //White
        for (int wm = 0; wm < 8; wm++) {
            if(wm == 0 || wm == 7){
                this.pieces.add(new Rook(true, 0 , wm));
                this.spaces[0][wm] = new Space(0, wm, new Rook(true, 0 , wm));
            } else if (wm == 1 || wm == 6) {
                this.pieces.add(new Knight(true, 0 , wm));
                this.spaces[0][wm] = new Space(0, wm, new Knight(true, 0, wm));
            } else if(wm == 2 || wm == 5){
                this.pieces.add(new Bishop(true, 0 , wm));
                this.spaces[0][wm] = new Space(0, wm, new Bishop(true, 0, wm));
            } else if(wm == 4){
                this.pieces.add(new Queen(true, 0 , wm));
                this.spaces[0][wm] = new Space(0, wm, new Queen(true, 0, wm));
            } else if(wm == 3){
                this.pieces.add(new King(true, 0 , wm));
                this.spaces[0][wm] = new Space(0, wm, new King(true, 0, wm));
            }
            else {
                this.spaces[0][wm] = new Space(0, wm, null);
            }
        }
        //Black
        for (int bm = 0; bm < 8; bm++) {
            if(bm == 0 || bm == 7){
                this.pieces.add(new Rook(false, 7 , bm));
                this.spaces[7][bm] = new Space(7, bm, new Rook(false, 7, bm));
            } else if(bm == 1 || bm == 6) {
                this.pieces.add(new Knight(false, 7 , bm));
                this.spaces[7][bm] = new Space(7, bm, new Knight(false, 7, bm));
            } else if(bm == 2 || bm == 5){
                this.pieces.add(new Bishop(false, 7 , bm));
                this.spaces[7][bm] = new Space(7, bm, new Bishop(false, 7, bm));
            } else if(bm == 4){
                this.pieces.add(new Queen(false, 7 , bm));
                this.spaces[7][bm] = new Space(7, bm, new Queen(false, 7, bm));
            } else if(bm == 3){
                this.pieces.add(new King(false, 7 , bm));
                this.spaces[7][bm] = new Space(7, bm, new King(false, 7, bm));
            }
            else {
                this.spaces[7][bm] = new Space(7, bm, null);
            }
        }

        //Set Pawns
        //White side
        for (int w = 0; w < 8; w++) {
            this.pieces.add(new Pawn(true, 1 , w));
            this.spaces[1][w] = new Space(1, w, new Pawn(true, 1, w));
        }
        //Black side
        for (int b = 0; b < 8; b++) {
            this.pieces.add(new Pawn(false, 6 , b));
            this.spaces[6][b] = new Space(6, b, new Pawn(false, 6, b));
        }
        //Set Empty spaces
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                this.spaces[i][j] = new Space(i, j, null);
            }
        }
    }

    public boolean checkCheck(Board board, Player player){
        //Filtering all pieces to be a list of enemies of passed king
        Stream<Piece> enemyTeam = this.pieces.stream().filter((piece) -> {
            return piece.isWhite() != player.isWhiteSide();
        });
        //Gets the player passed king
        Piece playerKing = this.pieces.stream()
                .filter((piece) -> piece.getName() == "King" )
                .filter((piece) -> piece.isWhite() == player.isWhiteSide())
                .findFirst()
                .get();
//        System.out.println(playerKing.getColor() + " King (" + playerKing.getX()  +"," + playerKing.getY() + ")");
//        System.out.println(playerKing.getColor() + " " + enemyTeam.findAny().get().getColor());

        //creating a new list of enemies that can attack the king passed
        Stream<Piece> possibleEnemies = enemyTeam.map((piece) -> {
            //calling the inherent functions on the pieces to check if its possible for them to get to the king
            if(piece.canMove(board, this.getBox(piece.getX(), piece.getY()), this.getBox(playerKing.getX(), playerKing.getY()))){
                return piece;
            }
            return null;
        });
        //if possible return true
        if (possibleEnemies.toArray().length > 0){
            System.out.println("True");

            return true;
        } else {
            System.out.println("False");

            return false;
        }
    }

    public Space getBox(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Index out of bounds");
            return null;
        }

        return this.spaces[x][y];
    }

    public Piece getPiece(int x, int y) {

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Index out of bounds");
            return null;
        }

        return this.spaces[x][y].getPiece();
    }

    @Override
    public String toString(){
        String boardDisplay = new String();
        //Building out spaces and numbering the X-axis
        boardDisplay += "---------------------------------\n";
        for (int sx = 0; sx < 8; sx++) {
            boardDisplay += sx;
            for (int sy = 0; sy < 8; sy++) {
                boardDisplay += this.spaces[sx][sy].toString();
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
