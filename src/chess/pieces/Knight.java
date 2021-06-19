package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class Knight extends Piece {
    public Knight(boolean white, int x, int y){
        super(white, x, y);
        this.setName("Knight");
    }

    @Override
    public boolean canMove(Board board, Space start, Space end){
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        //Check if there is a piece in the end space
        if(end.getPiece() != null){
            //Prevents moving into a space already occupied by the same color
            if(this.isWhite() == end.getPiece().isWhite()){
                return false;
            }
        }
        //Checking for valid movement
        //If x changes by two and y by 1 Successful
        if(x == 2 && y == 1){
            return true;
        }
        //If x changes by one and y by 2 Successful
        else if(x == 1 && y == 2){
            return true;
        }
        //Failed check
        return false;
    }
}
