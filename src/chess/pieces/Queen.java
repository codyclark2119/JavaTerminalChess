package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class Queen extends Piece {
    public Queen(boolean white){
        super(white);
    }
    @Override
    public boolean canMove(Board board, Space start, Space end){
        //Check if there is a piece of the same color in the end space
        if(end.getPiece().isWhite() == this.isWhite()){
            return false;
        }

        return false;
    }
}
