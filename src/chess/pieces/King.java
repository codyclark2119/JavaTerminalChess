package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class King extends Piece {
    private boolean castling = false;

    public King(boolean white, int x, int y){
        super(white, x, y);
        this.setName("King");
    }

    public boolean isCastling() {
        return castling;
    }

    public void setCastling(boolean castling) {
        this.castling = castling;
    }

    @Override
    public boolean canMove(Board board, Space start, Space end){
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = end.getX() - start.getX();
        int y = end.getY() - start.getY();
        //Not allowing more than one space movement for x axis
        if(Math.abs(x) >= 2){
            return false;
        }
        //Not allowing more than one space movement for y axis
        else if(Math.abs(y) >= 2){
            return false;
        }
        //Checking for legal diagonal movement
        if(Math.abs(x) == Math.abs(y)){
            if(this.diagonalMoveCheck(board, start, end)) {
                return true;
            }
        }
        //Checking for legal vertical movement
        else if(x != 0 && Math.abs(y) == 0){
            if(this.verticalMoveCheck(board, start, end)){
                return true;
            }
        }
        //Checking for legal horizontal movement
        else if(y != 0 && Math.abs(x) == 0){
            if(this.horizontalMoveCheck(board, start, end)) {
                return true;
            }
        }
        //Otherwise invalid movement
        return false;
    }
}
