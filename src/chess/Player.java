package chess;

public class Player {
    private boolean whiteSide;
    private boolean inCheck = false;
    public Player(boolean whiteSide){
        this.whiteSide = whiteSide;
    }

    public boolean isInCheck() {
        return inCheck;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
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
