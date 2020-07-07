package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class Pawn extends Piece {
    public Pawn(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Space start, Space end){
        //Check if there is a piece of the same color in the end space
        if(end.getPiece().isWhite() == this.isWhite()){
            return false;
        }
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        //Check to see if attacking
        if (x == 1 && y != 0) {
            if(end.getPiece() != null){
                return true;
            }
            //If Y is greater than 0 and there is no enemy piece returns false
            return false;
        }

        return false;
    }
}
