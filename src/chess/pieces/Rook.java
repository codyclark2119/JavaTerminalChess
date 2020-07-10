package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class Rook extends Piece {
    public Rook(boolean white){
        super(white);
        this.setName("Rook");
    }
    @Override
    public boolean canMove(Board board, Space start, Space end){
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        //If the rook has been moved only along the x axis successful
        if(x > 0 && y == 0){
            System.out.println("Successful move");
            return true;
        }
        //If the rook has been moved only along the y axis successful
        if(y > 0 && x == 0){
            System.out.println("Successful move");
            return true;
        }
        //Otherwise Failed check
        System.out.println("Invalid move");
        return false;
    }
}
