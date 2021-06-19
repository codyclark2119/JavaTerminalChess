package chess;

public class Player {
    private boolean whiteSide;
    private boolean inCheck = false;
    private boolean currentTurn;
    private boolean checkMate = false;

    public Player(boolean whiteSide, boolean currentTurn){
        this.whiteSide = whiteSide;
        this.currentTurn = currentTurn;
    }

    public boolean isInCheck() {
        return inCheck;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public void setCheckMate(boolean checkMate) {
        this.checkMate = checkMate;
    }

    public boolean isCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    public boolean isWhiteSide()
    {
        return this.whiteSide == true;
    }

    public String getColor(){
        if(this.whiteSide){
            return "White";
        }
        return "Black";
    }
}
