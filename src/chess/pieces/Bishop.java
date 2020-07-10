package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class Bishop extends Piece {
    public Bishop(boolean white){
        super(white);
        this.setName("Bishop");
    }
    @Override
    public boolean canMove(Board board, Space start, Space end){
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        //Bishop must always make an equal value change in each axis
        if(x == y && x > 0 ){
            System.out.println("Successful move");
            return true;
        }
        //Otherwise Failed check
        System.out.println("Invalid move");
        return false;
    }
}
