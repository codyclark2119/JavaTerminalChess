package chess.pieces;

import chess.*;

import java.util.ArrayList;
import java.util.List;

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

    public boolean checkValidMovement(int x, int y) {
        //Gets a space specifically if its in bounds
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        return true;
    }

    public List possibleMoves(Board board, Space start, Player player){
        //Getting the coordinates passed to check if
        //movement is valid
        int x = start.getX();
        int y = start.getY();
        //List holding all possible movements
        List<Move> moves = new ArrayList<>();
        // N
        if(checkValidMovement(x-1,y) && canMove(board, start, board.getBox(x-1, y))) {
            moves.add(new Move(player, start, board.getBox(x-1, y)));
        }
        // NE
        if(checkValidMovement(x-1, y+1) && canMove(board, start, board.getBox(x-1, y+1))) {
            moves.add(new Move(player, start, board.getBox(x-1, y + 1)));
        }
        // E
        if(checkValidMovement(x,y+1) && canMove(board, start, board.getBox(x,y+1))) {
            moves.add(new Move(player, start, board.getBox(x , y + 1)));
        }
        // SE
        if(checkValidMovement(x+1,y+1) && canMove(board, start, board.getBox(x+1,y+1))) {
            moves.add(new Move(player, start, board.getBox(x + 1, y + 1)));
        }
        // S
        if(checkValidMovement(x+1,y) && canMove(board, start, board.getBox(x+1,y))) {
            moves.add(new Move(player, start, board.getBox(x+1, y)));
        }
        // SW
        if(checkValidMovement(x+1,y-1) && canMove(board, start, board.getBox(x+1,y-1))) {
            moves.add(new Move(player, start, board.getBox(x + 1, y - 1)));
        }
        // W
        if(checkValidMovement(x,y-1) && canMove(board, start, board.getBox(x,y-1))) {
            moves.add(new Move(player, start, board.getBox(x, y-1)));
        }
        // NW
        if(checkValidMovement(x-1,y-1) && canMove(board, start, board.getBox(x-1,y-1))) {
            moves.add(new Move(player, start, board.getBox(x - 1, y - 1)));
        }
        return moves;
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
