package chess;

public abstract class Piece {
    private boolean killed = false;
    private boolean white = false;
    public Piece(boolean white){
        this.setWhite(white);
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }
    //Abstract method to pass to all pieces to make sure they all have
    //individualized movement patterns
    public abstract boolean canMove(Board board, Space start, Space end);
}
