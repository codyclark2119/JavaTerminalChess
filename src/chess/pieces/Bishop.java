package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class Bishop extends Piece {
    public Bishop(boolean white, int x, int y){
        super(white, x, y);
        this.setName("Bishop");
    }
    @Override
    public boolean canMove(Board board, Space start, Space end){
        if(!this.diagonalMoveCheck(board, start, end)){
            return false;
        }
        System.out.println("Successful move");
        return true;
    }
}
