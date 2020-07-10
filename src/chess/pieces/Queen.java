package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class Queen extends Piece {
    public Queen(boolean white, int x, int y){
        super(white, x, y);
        this.setName("Queen");
    }

    @Override
    public boolean canMove(Board board, Space start, Space end){
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = end.getX() - start.getX();
        int y = end.getY() - start.getY();
        //Checks the absolute value to check if Piece moves diagonally
        if(Math.abs(x) == Math.abs(y)){
            if(!this.diagonalMoveCheck(board, start, end)) {
                return false;
            }
        }
        //If the piece has been moved only along the x axis successful
        if(x != 0 && y == 0){
            if(!this.verticalMoveCheck(board, start, end)){
                return false;
            }
        }
        //If the queen has been moved only along the y axis successful
        if(y != 0 && x == 0){
            if(!this.horizontalMoveCheck(board, start, end)) {
                return false;
            }
        }
        System.out.println("Successful Move");
        return true;
    }
}
