package chess;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private boolean moved = false;
    private boolean white = false;
    private String name = new String();
    private boolean killed = false;
    private int x;
    private int y;

    public Piece(boolean white, int x, int y){
        this.setWhite(white);
        this.x = x;
        this.y = y;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isWhite() {
        return white;
    }

    public String getColor() {
        if(white){
            return "White";
        }
        return "Black";
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Abstract method to pass to all pieces to make sure they all have
    //individualized movement patterns
    public abstract boolean canMove(Board board, Space start, Space end);

    public boolean checkValidMovement(int x, int y, Board board, Space start) {
        //Gets a space specifically if its in bounds
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        if(!canMove(board, start, board.getBox(x, y))){
            return false;
        }
        return true;
    }

    public List possibleMoves(Board board, Space start, Player player){

        //List holding all possible movements
        List<Move> moves = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            //Getting the coordinates passed to check if
            //movement is valid
            int x = start.getX();
            int y = start.getY();
            switch (i){
                // N
                case 0:
                    while(checkValidMovement(x-1, y, board, start)){
                        moves.add(new Move(player, start, board.getBox(x-1, y)));
                        x--;
                    }
                    break;
                // NE
                case 1:
                    while(checkValidMovement(x-1, y+1, board, start)){
                        moves.add(new Move(player, start, board.getBox(x-1, y+1)));
                        x--;
                        y++;
                    }
                    break;
                // E
                case 2:
                    while(checkValidMovement(x,y+1, board, start)){
                        moves.add(new Move(player, start, board.getBox(x,y+1)));
                        y++;
                    }
                    break;
                // SE
                case 3:
                    while(checkValidMovement(x+1, y+1, board, start)){
                        moves.add(new Move(player, start, board.getBox(x+1, y+1)));
                        x++;
                        y++;
                    }
                    break;
                // S
                case 4:
                    while(checkValidMovement(x+1, y, board, start)){
                        moves.add(new Move(player, start, board.getBox(x+1, y)));
                        x++;
                    }
                    break;
                // SW
                case 5:
                    while(checkValidMovement(x+1, y-1, board, start)){
                        moves.add(new Move(player, start, board.getBox(x+1, y-1)));
                        x++;
                        y--;
                    }
                    break;
                // W
                case 6:
                    while(checkValidMovement(x,y-1, board, start)){
                        moves.add(new Move(player, start, board.getBox(x,y-1)));
                        y--;
                    }
                    break;
                // NW
                case 7:
                    while(checkValidMovement(x-1, y-1, board, start)){
                        moves.add(new Move(player, start, board.getBox(x-1, y-1)));
                        x--;
                        y--;
                    }
                    break;
            }
        }
        return moves;
    }

    public boolean diagonalMoveCheck(Board board, Space start, Space end){
        try {
            //Getting the difference between coordinates passed to check if
            //movement amount is valid
            int x = end.getX() - start.getX();
            int y = end.getY() - start.getY();
            int checkX = start.getX();
            int checkY = start.getY();
            //Checks the absolute value to check if Piece moves diagonally
            if (Math.abs(x) == Math.abs(y)) {
                for (int m = 0; m < Math.abs(x); m++) {
                    //If greater than 0 check the next 1 space to the bottom
                    if (x > 0) {
                        checkX++;
                    }
                    //Otherwise check to the top
                    else if (x < 0) {
                        checkX--;
                    }
                    //If greater than 0 check the next 1 space to the right
                    if (y > 0) {
                        checkY++;
                    }
                    //Otherwise check to the left
                    else if (y < 0) {
                        checkY--;
                    }
                    //After the increment check to see if that space has a piece
                    if (board.getBox(checkX, checkY).getPiece() != null) {
                        Piece foundPiece = board.getBox(checkX, checkY).getPiece();
                        //Check for same color in the way to the end space chosen
                        if (foundPiece.isWhite() == this.isWhite()) {
                            return false;
                        }
                        //fails the check if a piece in the way to the end point
                        else if (foundPiece.isWhite() != this.isWhite() && checkX != end.getX()) {
                            return false;
                        } else {
                            foundPiece.setKilled(true);
                            //Allow attack if at the end point
                            return true;
                        }
                    }
                }
                //Doesn't allow any diagonal movement for pawns unless there are successful attacks
                if (this.getName() == "Pawn") {
                    return false;
                }
                //Otherwise its a successful diagonal move
                return true;
            }
        }
        catch (Exception e){
            System.out.println("Diagonal movement fail");
        }
        //Failed check if the movement wasn't an equal difference
        return false;
    }

    public boolean verticalMoveCheck(Board board, Space start, Space end) {
        try {
            //Getting the difference between coordinates passed to check if
            //movement amount is valid
            int x = end.getX() - start.getX();
            int y = end.getY() - start.getY();
            int checkX = start.getX();
            int checkY = start.getY();
            //If the piece has been moved only along the x axis
            if (x != 0 && y == 0) {
                //Making a check at every space for any pieces in the way
                for (int m = 0; m < Math.abs(x); m++) {
                    //Moves the space selector up the x axis one
                    if (x < 0) {
                        checkX--;
                    }
                    //Moves the space selector down the x axis one
                    else if (x > 0) {
                        checkX++;
                    }
                    //After the increment check to see if that space has a piece
                    if (board.getBox(checkX, checkY).getPiece() != null) {
                        Piece foundPiece = board.getBox(checkX, checkY).getPiece();
                        //Check for same color in the way to the end space chosen
                        if (foundPiece.isWhite() == this.isWhite()) {
                            return false;
                        }
                        //If it has encountered an enemy before selected end point
                        else if (foundPiece.isWhite() != this.isWhite() && checkX != end.getX()) {
                            return false;
                        } else {
                            //Doesn't allow any vertical movement for pawns to attack
                            if (this.getName() == "Pawn") {
                                return false;
                            }
                            foundPiece.setKilled(true);
                            //Otherwise successfully attacks and moves
                            return true;
                        }
                    }
                }
                //If no obstruction on the way to end point successful move
                return true;
            }
        }
        catch (Exception e){
            System.out.println("Vertical movement fail");
        }
        //Otherwise Failed check
        return false;
    }


    public boolean horizontalMoveCheck(Board board, Space start, Space end){
        try {
            //Getting the difference between coordinates passed to check if
            //movement amount is valid
            int x = end.getX() - start.getX();
            int y = end.getY() - start.getY();
            int checkX = start.getX();
            int checkY = start.getY();
            //If the piece has been moved only along the y axis successful
            if(y != 0 && x == 0){
                //Making a check at every space for any pieces in the way
                for(int n = 0; n < Math.abs(y); n++){
                    //Moves the space selector right the y axis one
                    if(y < 0){
                        checkY--;
                    }
                    //Moves the space selector left the y axis one
                    else if (y > 0) {
                        checkY++;
                    }
                    //After the increment check to see if that space has a piece
                    if(board.getBox(checkX,checkY).getPiece() != null){
                        Piece foundPiece = board.getBox(checkX,checkY).getPiece();
                        //Check for same color in the way to the end space chosen
                        if(foundPiece.isWhite() == this.isWhite()){
                            return false;
                        }
                        //If it has encountered an enemy before selected end point
                        else if(foundPiece.isWhite() != this.isWhite() && checkX != end.getX()){
                            return false;
                        } else {
                            foundPiece.setKilled(true);
                            //Otherwise successfully attacks and moves
                            return true;
                        }
                    }
                }
                //If no obstruction on the way to end point successful move
                return true;
            }
        }
        catch (Exception e){
            System.out.println("Horizontal movement fail");
        }
        //Otherwise Failed check
        return false;
    }
}
