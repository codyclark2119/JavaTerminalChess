package chess;

public abstract class Piece {
    private boolean killed = false;
    private boolean white = false;
    private String name = new String();
    private int x;
    private int y;

    public Piece(boolean white, int x, int y){
        this.setWhite(white);
        this.x = x;
        this.y = y;
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

    public boolean diagonalMoveCheck(Board board, Space start, Space end){
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = end.getX() - start.getX();
        int y = end.getY() - start.getY();
        int checkX = start.getX();
        int checkY = start.getY();
        //Checks the absolute value to check if Piece moves diagonally
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
                    Piece foundPiece = board.getBox(checkX,checkY).getPiece();
                    //Check for same color in the way to the end space chosen
                    if(foundPiece.isWhite() == this.isWhite()){
                        System.out.println(foundPiece.getColor() + " " + foundPiece.getName() + " at (" + checkX + ","+ checkY + ")");
                        return false;
                    }
                    else if(foundPiece.isWhite() != this.isWhite() && checkX != end.getX()){
                        System.out.println(foundPiece.getColor() + " " + foundPiece.getName() + " at (" + checkX + ","+ checkY + ")");
                        return false;
                    } else {
                        System.out.println(this.getColor() + " " + this.getName() + " Attacks " + foundPiece.getColor() + " " + foundPiece.getName() + " at (" + checkX + ","+ checkY + ")");
                        return true;
                    }
                }
            }
            System.out.println("Successful Diagonal move");
            return true;
        }
        //Otherwise Failed check
        System.out.println("Invalid Diagonal Move");
        return false;
    }

    public boolean verticalMoveCheck(Board board, Space start, Space end){
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = end.getX() - start.getX();
        int y = end.getY() - start.getY();
        int checkX = start.getX();
        int checkY = start.getY();
        //If the piece has been moved only along the x axis successful
        if(x != 0 && y == 0){
            for(int m = 0; m < Math.abs(x); x++){
                if (x < 0){
                    checkX--;
                }
                else if (x > 0) {
                    checkX++;
                }
                //After the increment check to see if that space has a piece
                if(board.getBox(checkX,checkY).getPiece() != null){
                    Piece foundPiece = board.getBox(checkX,checkY).getPiece();
                    //Check for same color in the way to the end space chosen
                    if(foundPiece.isWhite() == this.isWhite()){
                        System.out.println(foundPiece.getColor() + " " + foundPiece.getName() + " at (" + checkX + ","+ checkY + ")");
                        return false;
                    }
                    else if(foundPiece.isWhite() != this.isWhite() && checkX != end.getX()){
                        System.out.println(foundPiece.getColor() + " " + foundPiece.getName() + " at (" + checkX + ","+ checkY + ")");
                        return false;
                    } else {
                        System.out.println(this.getColor() + " " + this.getName() + " Attacks " + foundPiece.getColor() + " " + foundPiece.getName() + " at (" + checkX + ","+ checkY + ")");
                        return true;
                    }
                }
            }
            if(this.getName() == "Pawn"){
                System.out.println("Pawn cannot move diagonal");
                return false;
            }
            System.out.println("Successful Vertical Move");
            return true;
        }
        //Otherwise Failed check
        System.out.println("Invalid Vertical move");
        return false;
    }

    public boolean horizontalMoveCheck(Board board, Space start, Space end){
        //Getting the difference between coordinates passed to check if
        //movement amount is valid
        int x = end.getX() - start.getX();
        int y = end.getY() - start.getY();
        int checkX = start.getX();
        int checkY = start.getY();
        //If the piece has been moved only along the y axis successful
        if(y != 0 && x == 0){
            for(int n = 0; n < Math.abs(y); n++){
                if(y < 0){
                    checkY--;
                } else if (y > 0) {
                    checkY++;
                }
                //After the increment check to see if that space has a piece
                if(board.getBox(checkX,checkY).getPiece() != null){
                    Piece foundPiece = board.getBox(checkX,checkY).getPiece();
                    //Check for same color in the way to the end space chosen
                    if(foundPiece.isWhite() == this.isWhite()){
                        System.out.println(foundPiece.getColor() + " " + foundPiece.getName() + " at (" + checkX + ","+ checkY + ")");
                        return false;
                    }
                    else if(foundPiece.isWhite() != this.isWhite() && checkX != end.getX()){
                        System.out.println(foundPiece.getColor() + " " + foundPiece.getName() + " at (" + checkX + ","+ checkY + ")");
                        return false;
                    } else {
                        System.out.println(this.getColor() + " " + this.getName() + " Attacks " + foundPiece.getColor() + " " + foundPiece.getName() + " at (" + checkX + ","+ checkY + ")");
                        return true;
                    }
                }
            }
            System.out.println("Successful Horizontal Move");
            return true;
        }
        //Otherwise Failed check
        System.out.println("Invalid Horizontal move");
        return false;
    }
}
