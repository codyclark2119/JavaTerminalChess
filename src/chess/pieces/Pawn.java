package chess.pieces;

import chess.Board;
import chess.Piece;
import chess.Space;

public class Pawn extends Piece {

    public Pawn(boolean white){
        super(white);
        this.setName("Pawn");
    }

    @Override
    public boolean canMove(Board board, Space start, Space end){
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = start.getX() - end.getX();
        int y = start.getY() - end.getY();
        //Check if there is a piece of the same color in the end space
        if(end.getPiece() != null){
            //Prevents moving into a space already occupied by the same color
            if(this.isWhite() == end.getPiece().isWhite() || y == 0){
                System.out.println("Space Occupied");
                return false;
            }
        }
        //Prevents a piece from moving across the y axis
        //and there is no enemy piece
        if (y != 0) {
            //Checks if end point is occupied by enemy pieces
            if(this.isWhite() != end.getPiece().isWhite()){
                System.out.println("Successful Attack");
                return true;
            }
            System.out.println("Invalid move");
            return false;
        }
        //Checking for valid first pawn two space movement
        //White
        if(start.getX() == 1 && this.isWhite() && x == -2){
            System.out.println("Successful move");
            return true;
        }
        //Black
        if(start.getX() == 6 && this.isWhite() && x == 2){
            System.out.println("Successful move");
            return true;
        }
        //Prevents white backwards moving
        if(this.isWhite() && x >= 1){
            System.out.println("Backwards moving not allowed");
            return false;
        }
        //Prevents black backwards moving
        if(this.isWhite() == false && x <= -1){
            System.out.println("Backwards moving not allowed");
            return false;
        }
        //Successful check
        System.out.println("Successful move");
        return true;
    }
}
