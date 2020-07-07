package chess;

public class Player {
    public boolean whiteSide;

    public Player(boolean whiteSide){
        this.whiteSide = whiteSide;
    }

    public boolean isWhiteSide()
    {
        return this.whiteSide == true;
    }

}
