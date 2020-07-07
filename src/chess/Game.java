package chess;

import java.util.List;

public class Game {
    private Player[] players;
    private Board board;
    private Player currentTurn;
    private List<Move> movesPlayed;

    private void initialize(Player p1, Player p2)
    {
        //Setting players
        players[0] = p1;
        players[1] = p2;
        //Resetting the board
        board.resetBoard();
        //Setting initial turn for whoever picked white side
        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        }
        else {
            this.currentTurn = p2;
        }
        //Clearing any previous game moves
        movesPlayed.clear();
    }

    public boolean playerMove(Player player, int startX,
                              int startY, int endX, int endY) {
        //Getting the start space from the board
        Space startBox = board.getBox(startX, startY);
        //Getting the space to move to from the board
        Space endBox = board.getBox(endX, endY);
        //Creating a move to send to the make move function
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player){
        //Getting the piece being moved
        Piece chosenPiece = move.getPieceMoved();
        //Checking if piece exists
        if(chosenPiece == null){
            return false;
        }
        //Checking if it is that players turn
        if(player != currentTurn){
            return false;
        }
        //Making sure the piece belongs to the player
        if(chosenPiece.isWhite() != player.isWhiteSide()){
            return false;
        }
        //Checks to see if the move is valid according the the pieces pattern
        if(!chosenPiece.canMove(board, move.getStart(), move.getEnd())){
            return false;
        }
        //Check for if there is an enemy piece in the end space
        //and sets their death
        Piece enemyPiece = move.getPieceKilled();
        if (enemyPiece != null) {
            enemyPiece.setKilled(true);
        }
        // store the move in a list
        movesPlayed.add(move);
        // move piece from the stat box to end box
        move.getEnd().setPiece(move.getPieceMoved());
        move.getStart().setPiece(null);
        // set the current turn to the other player
        if (this.currentTurn == players[0]) {
            this.currentTurn = players[1];
        }
        else {
            this.currentTurn = players[0];
        }

        return true;
    }
}
