package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class Rook extends Piece {
    public Rook(boolean white, int x, int y){
        super(white, x, y);
        this.setName("Rook");
    }
    @Override
    public boolean canMove(Board board, Space start, Space end){
        if(!this.verticalMoveCheck(board, start, end)){
            return false;
        }
        else if(!this.horizontalMoveCheck(board, start, end)){
            return false;
        }
        System.out.println("Successful Move");
        return true;
    }
}
