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
                Rook whiteRook = new Rook(true, 0 , wm);
                this.pieces.add(whiteRook);
                this.spaces[0][wm] = new Space(0, wm, whiteRook);
            } else if (wm == 1 || wm == 6) {
                Knight whiteKnight = new Knight(true, 0 , wm);
                this.pieces.add(whiteKnight);
                this.spaces[0][wm] = new Space(0, wm, whiteKnight);
            } else if(wm == 2 || wm == 5){
                Bishop whiteBishop = new Bishop(true, 0 , wm);
                this.pieces.add(whiteBishop);
                this.spaces[0][wm] = new Space(0, wm, whiteBishop);
            } else if(wm == 4){
                Queen whiteQueen = new Queen(true, 0 , wm);
                this.pieces.add(whiteQueen);
                this.spaces[0][wm] = new Space(0, wm, whiteQueen);
            } else if(wm == 3){
                King whiteKing = new King(true, 0 , wm);
                this.pieces.add(whiteKing);
                this.spaces[0][wm] = new Space(0, wm, whiteKing);
            }
            else {
                this.spaces[0][wm] = new Space(0, wm, null);
            }
        }
        //Black
        for (int bm = 0; bm < 8; bm++) {
            if(bm == 0 || bm == 7){
                Rook blackRook = new Rook(false, 7 , bm);
                this.pieces.add(blackRook);
                this.spaces[7][bm] = new Space(7, bm, blackRook);
            } else if(bm == 1 || bm == 6) {
                Knight blackKnight = new Knight(false, 7 , bm);
                this.pieces.add(blackKnight);
                this.spaces[7][bm] = new Space(7, bm, blackKnight);
            } else if(bm == 2 || bm == 5){
                Bishop blackBishop = new Bishop(false, 7 , bm);
                this.pieces.add(blackBishop);
                this.spaces[7][bm] = new Space(7, bm, blackBishop);
            } else if(bm == 4){
                Queen blackQueen = new Queen(false, 7 , bm);
                this.pieces.add(blackQueen);
                this.spaces[7][bm] = new Space(7, bm, blackQueen);
            } else if(bm == 3){
                King blackKing = new King(false, 7 , bm);
                this.pieces.add(blackKing);
                this.spaces[7][bm] = new Space(7, bm, blackKing);
            }
            else {
                this.spaces[7][bm] = new Space(7, bm, null);
            }
        }

        //Set Pawns
        //White side
        for (int w = 0; w < 8; w++) {
            Pawn whitePawn = new Pawn(true, 1 , w);
            this.pieces.add(whitePawn);
            this.spaces[1][w] = new Space(1, w, whitePawn);
        }
        //Black side
        for (int b = 0; b < 8; b++) {
            Pawn blackPawn = new Pawn(false, 6 , b);
            this.pieces.add(blackPawn);
            this.spaces[6][b] = new Space(6, b, blackPawn);
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

        Space kingSpace = getBox(playerKing.getX(), playerKing.getY());
        ArrayList<Piece> possibleEnemies = new ArrayList<>();
        //creating a new list of enemies that can attack the king passed
        enemyTeam.forEach((piece) -> {
            Space enemySpace = getBox(piece.getX(), piece.getY());
            //calling the inherent functions on the pieces to check if its possible for them to get to the king
            if(piece.canMove(board, enemySpace, kingSpace)){
                System.out.println(piece.getColor() + " " + piece.getName() + " " + piece.getX() + "," + piece.getY());
                possibleEnemies.add(piece);
            }
        });

        //if possible return true
        if (possibleEnemies.toArray().length > 0){
            System.out.println(possibleEnemies.toArray().length);
            return true;
        } else {
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
