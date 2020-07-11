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
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = Math.abs(end.getX() - start.getX());
        int y = Math.abs(end.getY() - start.getY());
        //Checks the absolute value to check if Piece moves diagonally equally
        if(x == y){
            if(this.diagonalMoveCheck(board, start, end)) {
                return true;
            }
        }
        //Otherwise invalid movement
        return false;
    }
}
