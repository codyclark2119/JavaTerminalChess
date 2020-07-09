package chess;

public class Space {
    private int x;
    private int y;
    private Piece piece;

    public Space(int x, int y, Piece piece){
        this.setX(x);
        this.setY(y);
        this.setPiece(piece);
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

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString(){
        String spaceString = "|";
        if (this.piece == null){
            spaceString += "  |";
        } else {
            switch(this.piece.getName()){
                case "Pawn":
                    if(this.piece.isWhite()){
                        spaceString += "WP|";
                    } else {
                        spaceString += "BP|";
                    }
                    break;
                case "Knight":
                    if(this.piece.isWhite()){
                        spaceString += "WK|";
                    } else {
                        spaceString += "BK|";
                    }
                    break;
                case "Rook":
                    if(this.piece.isWhite()){
                        spaceString += "WR|";
                    } else {
                        spaceString += "BR|";
                    }
                    break;
            }
        }
        return spaceString;
    }
}
