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
    public boolean canMove(Board board, Space start, Space end) {
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = Math.abs(end.getX() - start.getX());
        int y = Math.abs(end.getY() - start.getY());
        //If the piece has been moved only along the x axis successful
        if (x > 0 && y == 0) {
            if (this.verticalMoveCheck(board, start, end)) {
                return true;
            }
        }
        //If the piece has been moved only along the y axis successful
        else if (y > 0 && x == 0) {
            if (this.horizontalMoveCheck(board, start, end)) {
                return true;
            }
        }
        //Otherwise invalid movement
        return false;
    }
}
