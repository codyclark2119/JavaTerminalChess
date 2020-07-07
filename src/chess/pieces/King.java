package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class King extends Piece {
    private boolean castling = false;

    public King(boolean white){
        super(white);
    }

    public boolean isCastling() {
        return castling;
    }

    public void setCastling(boolean castling) {
        this.castling = castling;
    }

    @Override
    public boolean canMove(Board board, Space start, Space end){
        //Check if there is a piece of the same color in the end space
        if(end.getPiece().isWhite() == this.isWhite()){
            return false;
        }
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        //Checks to make sure it didn't move more than one space in either direction
        if (x <= 1 && y <= 1) {
            // Need to write check for if other pieces put the king in check
            return true;
        }

        return false;
    }

}
