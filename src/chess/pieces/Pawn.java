package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class Pawn extends Piece {

    public Pawn(boolean white){
        super(white);
        this.setName("Pawn");
    }

    @Override
    public boolean canMove(Board board, Space start, Space end){
        //Check if there is a piece of the same color in the end space
        if(end.getPiece() != null){
            if(this.isWhite() == end.getPiece().isWhite()){
                System.out.println("Space Occupied");
                return false;
            }
        }
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = start.getX() - end.getX();
        int y = start.getY() - end.getY();

        //Check to see if attacking
        if (y != 0) {
            if(end.getPiece() != null){
                return true;
            }
            System.out.println("Incorrect move");
            //If Y is greater than 0 and there is no enemy piece returns false
            return false;
        }
        if(this.isWhite() && x == -1){
            System.out.println("Successful move");
            return true;
        }
        if(this.isWhite() == false && x == 1){
            System.out.println("Successful move");
            return true;
        }
        System.out.println("Pawns may not move backwards");
        return false;
    }
}
