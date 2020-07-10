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
        if(Math.abs(x) <= 1 && Math.abs(y) <= 1){
            if(!this.diagonalMoveCheck(board, start, end)){
                return false;
            }
            else if(!this.verticalMoveCheck(board, start, end)){
                return false;
            }
            else if(!this.horizontalMoveCheck(board, start, end)){
                return false;
            }
            System.out.println("Successful Move");
            return true;
        }
        System.out.println("Invalid Move");
        return false;
    }
}
