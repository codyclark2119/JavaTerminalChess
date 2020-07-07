package chess;

public class Move {
    private Player player;
    private Space start;
    private Space end;
    private Piece pieceMoved;
    private Piece pieceKilled;
    private boolean castlingMove = false;

    public Move(Player player, Space start, Space end)
    {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
        this.pieceKilled = end.getPiece();
    }

    public boolean isCastlingMove()
    {
        return this.castlingMove == true;
    }

    public void setCastlingMove(boolean castlingMove)
    {
        this.castlingMove = castlingMove;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public Piece getPieceKilled() {
        return pieceKilled;
    }

    public Space getStart() {
        return start;
    }

    public Space getEnd() {
        return end;
    }
}
