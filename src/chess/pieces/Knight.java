package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class Knight extends Piece {
    public Knight(boolean white){
        super(white);
        this.setName("Knight");
    }

    @Override
    public boolean canMove(Board board, Space start, Space end){
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        //Check if there is a piece of the same color in the end space
        if(end.getPiece() != null){
            //Prevents moving into a space already occupied by the same color
            if(this.isWhite() == end.getPiece().isWhite()){
                System.out.println("Space Occupied");
                return false;
            }
            //If end point is occupied by enemy piece attacks
            else {
                System.out.println("Successful Attack");
                return true;
            }
        }
        System.out.println(x);
        System.out.println(y);
        //Checking for valid movement
        if(x == 2 && y == 1){
            System.out.println("Successful move");
            return true;
        }
        if(x == 1 && y == 2){
            System.out.println("Successful move");
            return true;
        }
        //Successful check
        System.out.println("Invalid move");
        return false;
    }
}
