package chess;

import chess.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Board {
    Space[][] spaces;
    ArrayList<Piece> pieces;

    public Board(){
        this.spaces = new Space[8][8];
        this.pieces = new ArrayList<>();
    }

    public void resetBoard(){
        try {
            if (this.pieces.size() > 0) {
                this.pieces.clear();
            }
            //Set Major characters on their respective spaces
            //and adding them to a list for checks on all pieces
            //White
            for (int wm = 0; wm < 8; wm++) {
                if (wm == 0 || wm == 7) {
                    Rook whiteRook = new Rook(true, 0, wm);
                    this.pieces.add(whiteRook);
                    this.spaces[0][wm] = new Space(0, wm, whiteRook);
                } else if (wm == 1 || wm == 6) {
                    Knight whiteKnight = new Knight(true, 0, wm);
                    this.pieces.add(whiteKnight);
                    this.spaces[0][wm] = new Space(0, wm, whiteKnight);
                } else if (wm == 2 || wm == 5) {
                    Bishop whiteBishop = new Bishop(true, 0, wm);
                    this.pieces.add(whiteBishop);
                    this.spaces[0][wm] = new Space(0, wm, whiteBishop);
                } else if (wm == 4) {
                    Queen whiteQueen = new Queen(true, 0, wm);
                    this.pieces.add(whiteQueen);
                    this.spaces[0][wm] = new Space(0, wm, whiteQueen);
                } else if (wm == 3) {
                    King whiteKing = new King(true, 0, wm);
                    this.pieces.add(whiteKing);
                    this.spaces[0][wm] = new Space(0, wm, whiteKing);
                } else {
                    this.spaces[0][wm] = new Space(0, wm, null);
                }
            }
            //Black
            for (int bm = 0; bm < 8; bm++) {
                if (bm == 0 || bm == 7) {
                    Rook blackRook = new Rook(false, 7, bm);
                    this.pieces.add(blackRook);
                    this.spaces[7][bm] = new Space(7, bm, blackRook);
                } else if (bm == 1 || bm == 6) {
                    Knight blackKnight = new Knight(false, 7, bm);
                    this.pieces.add(blackKnight);
                    this.spaces[7][bm] = new Space(7, bm, blackKnight);
                } else if (bm == 2 || bm == 5) {
                    Bishop blackBishop = new Bishop(false, 7, bm);
                    this.pieces.add(blackBishop);
                    this.spaces[7][bm] = new Space(7, bm, blackBishop);
                } else if (bm == 4) {
                    Queen blackQueen = new Queen(false, 7, bm);
                    this.pieces.add(blackQueen);
                    this.spaces[7][bm] = new Space(7, bm, blackQueen);
                } else if (bm == 3) {
                    King blackKing = new King(false, 7, bm);
                    this.pieces.add(blackKing);
                    this.spaces[7][bm] = new Space(7, bm, blackKing);
                } else {
                    this.spaces[7][bm] = new Space(7, bm, null);
                }
            }
            //Set Pawns
            //White side
            for (int w = 0; w < 8; w++) {
                Pawn whitePawn = new Pawn(true, 1, w);
                this.pieces.add(whitePawn);
                this.spaces[1][w] = new Space(1, w, whitePawn);
            }
            //Black side
            for (int b = 0; b < 8; b++) {
                Pawn blackPawn = new Pawn(false, 6, b);
                this.pieces.add(blackPawn);
                this.spaces[6][b] = new Space(6, b, blackPawn);
            }
            //Set Empty spaces
            for (int i = 2; i < 6; i++) {
                for (int j = 0; j < 8; j++) {
                    this.spaces[i][j] = new Space(i, j, null);
                }
            }
        } catch (Exception e) {
            System.out.println("Bad Board build");
        }
    }

    public boolean checkCheck(Board board, Player player){
        try {
            //Filtering all pieces to be a list of enemies of passed player
            Stream<Piece> enemyTeam = this.pieces.stream().filter((piece) -> {
                return piece.isWhite() != player.isWhiteSide();
            });
            //Gets the player passed king
            Piece playerKing = this.pieces.stream()
                    .filter((piece) -> piece.getName() == "King")
                    .filter((piece) -> piece.isWhite() == player.isWhiteSide())
                    .findFirst()
                    .get();
            //Setting a reference for the space holding the king
            Space kingSpace = getBox(playerKing.getX(), playerKing.getY());
            //Creating a list of possible enemies
            ArrayList<Piece> possibleEnemies = new ArrayList<>();
            //creating a new list of enemies that can attack the king passed
            enemyTeam.forEach((piece) -> {
                //Getting the current space of an enemy
                Space enemySpace = getBox(piece.getX(), piece.getY());
                //Checking if the enemy can move to the kings from their current space
                if (piece.canMove(board, enemySpace, kingSpace)) {
                    //If possible add to list
                    possibleEnemies.add(piece);
                }
            });
            //If there were enemies that can reach the king validate the check status
            if (possibleEnemies.toArray().length > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Fail to run check");
        }
        return false;
    }

    public boolean checkCheckMate(Board board, Player player){

            //Filtering all pieces to be a list of enemies of passed player
            Stream<Piece> enemyTeam = this.pieces.stream().filter((piece) -> {
                return piece.isWhite() != player.isWhiteSide();
            });
            //Gets the player passed king
            King playerKing = (King) this.pieces.stream()
                    .filter((piece) -> piece.getName() == "King")
                    .filter((piece) -> piece.isWhite() == player.isWhiteSide())
                    .findFirst()
                    .get();
            //Setting a reference for the space holding the king
            Space kingSpace = getBox(playerKing.getX(), playerKing.getY());
            //Getting all the possible available moves for the king
            List<Move> possibleMoves = playerKing.possibleMoves(board, kingSpace, player);
            //Creating a list of possible enemies
            ArrayList<Piece> possibleEnemies = new ArrayList<>();
            //Iterating list of enemies of the king
            enemyTeam.forEach((piece) -> {
                //Getting the current space of enemy
                Space enemySpace = getBox(piece.getX(), piece.getY());
                //Checking if the enemy can move to the kings
                //possible spaces from their current space
                possibleMoves.stream().forEach((move) -> {
                    if (piece.canMove(board, enemySpace, move.getEnd())) {
                        //If possible add to list
                        possibleEnemies.add(piece);
                    }
                });
            });
        System.out.println("Enemies that can attack: " + possibleEnemies.toArray().length + "\nPossible King Moves: "+ possibleMoves.size());
            //If there were enemies that can reach the king validate the check status
            if (possibleEnemies.toArray().length == possibleMoves.size() && possibleMoves.size() > 0) {
                System.out.println(possibleEnemies.toArray().length);
                return true;
            } else {
                return false;
            }
    }

    public Space getBox(int x, int y) {
        //Gets a space specifically if its in bounds
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return null;
        }
        return this.spaces[x][y];
    }

    public Piece getPiece(int x, int y) {
        //Gets a piece specifically if its in bounds
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return null;
        }
        return this.spaces[x][y].getPiece();
    }

    public void displayBoard(){
        //Prints the board using the overwritten to string from the board class
        System.out.println(this.toString());
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
        boardDisplay += "\n---------------------------------";

        return boardDisplay;
    }
}
