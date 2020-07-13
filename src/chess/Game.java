package chess;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Move> movesPlayed = new ArrayList();

    public void listMoves() {
        //Print a line of each move played
        for(int i = 0; i < movesPlayed.size(); i++){
            System.out.println(movesPlayed.get(i).toString());
        }
    }

    public boolean makeMove(Board board, Move move, Player player) {
        try{
            //Getting the piece being moved
            Piece chosenPiece = move.getPieceMoved();
            //Checking if piece exists
            if (chosenPiece == null) {
                System.out.println("Chosen null");
                return false;
            }
            //Checking if it is that players turn
            if (!player.isCurrentTurn()) {
                System.out.println("Out of turn");
                return false;
            }
            //Making sure the piece belongs to the player
            if (chosenPiece.isWhite() != player.isWhiteSide()) {
                System.out.println("Not your piece");
                return false;
            }
            //Checks to see if the move is valid according the the pieces pattern
            if (!chosenPiece.canMove(board, move.getStart(), move.getEnd())) {
                System.out.println("Invalid move");

                return false;
            }
            // move piece from the start box to end box
            move.getEnd().setPiece(move.getPieceMoved());
            //Updating the pieces inherent x y properties
            chosenPiece.setX(move.getEnd().getX());
            chosenPiece.setY(move.getEnd().getY());
            //Clearing start space
            move.getStart().setPiece(null);
            //check if originally in check
            if(player.isInCheck()){
                //If out of check
                if(!board.checkCheck(board, player)){
                    //Clears original check
                    player.setInCheck(false);
                    // store the move in a list if out of check
                    movesPlayed.add(move);
                    return true;
                } else {
                    System.out.println("\nFailed to move out of check\n");
                    //Reset values back to start
                    move.getStart().setPiece(move.getPieceMoved());
                    //Updating the pieces inherent x y properties
                    chosenPiece.setX(move.getStart().getX());
                    chosenPiece.setY(move.getStart().getY());
                    //Clear end space
                    move.getEnd().setPiece(null);
                    //and fail the move
                    return false;
                }
            } else {
                //If was never in check goes ahead and
                // stores the move in a list
                movesPlayed.add(move);
                return true;
            }
        } catch (Exception e){
            System.out.println("Error Creating movement");
        }
        //return successful move
        return true;
    }
}
