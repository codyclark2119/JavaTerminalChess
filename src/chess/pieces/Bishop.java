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
        int x = end.getX() - start.getX();
        int y = end.getY() - start.getY();
        int checkX = start.getX();
        int checkY = start.getY();
        //Bishop must always make an equal value change in each axis
        if(Math.abs(x) == Math.abs(y)){
            for(int m = 0; m < Math.abs(x); m++){
                //If greater than 0 check the next 1 space to the bottom
                if(x > 0){
                    checkX++;
                }
                //Otherwise check to the top
                else if (x < 0){
                    checkX--;
                }
                //If greater than 0 check the next 1 space to the right
                if(y > 0){
                    checkY++;
                }
                //Otherwise check to the left
                else if(y < 0){
                    checkY--;
                }
                //After the increment check to see if that space has a piece
                if(board.getBox(checkX,checkY).getPiece() != null){
                    //Check for same color in the way to the end space chosen
                    if(board.getBox(checkX,checkY).getPiece().isWhite() == this.isWhite()){
                        System.out.println(board.getBox(checkX,checkY).getPiece().getName() + " at (" + checkX + ","+ checkY + ")");
                        return false;
                    }
                    else if(board.getBox(checkX,checkY).getPiece().isWhite() != this.isWhite() && checkX != end.getX()){
                        System.out.println(board.getBox(checkX,checkY).getPiece().getName() + " at (" + checkX + ","+ checkY + ")");
                        return false;
                    }
                }
            }
            System.out.println("Successful move");
            return true;
        }
        //Otherwise Failed check
        System.out.println("Invalid move");
        return false;
    }
}
