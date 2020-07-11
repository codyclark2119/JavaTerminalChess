package chess;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player[] players = new Player[2];
    private Board board = new Board();
    private Player currentTurn;
    private List<Move> movesPlayed = new ArrayList();

    public void initialize(Player p1, Player p2) {
        try {
            //Setting players
            players[0] = p1;
            players[1] = p2;
            //Resetting the board
            board.resetBoard();
            displayBoard();
            System.out.println("\n           Game Start\n");
            //Setting initial turn for whoever picked white side
            if (p1.isWhiteSide()) {
                this.currentTurn = p1;
            } else {
                this.currentTurn = p2;
            }
            //Clearing any previous game moves.
            if (movesPlayed.size() > 0) {
                movesPlayed.clear();
            }
        } catch (Exception e) {
            System.out.println("Initialization failure");
        }
    }

    public void listMoves() {
        //Print a line of each move played
        for(int i = 0; i < movesPlayed.size(); i++){
            System.out.println(movesPlayed.get(i).toString());
        }
    }

    public void changeTurn(Player player){
        // set the current turn to the other player
        if (player == players[0]) {
            this.currentTurn = players[1];
        } else {
            this.currentTurn = players[0];
        }
    }

    public void displayBoard(){
        //Prints the board using the overwritten to string from the board class
        System.out.println(board.toString());
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) {
        //Getting the start space from the board
        Space startBox = board.getBox(startX, startY);
        //Getting the space to move to from the board
        Space endBox = board.getBox(endX, endY);
        //Creating a move to send to the make move function
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) {
        try{
            //Getting the piece being moved
            Piece chosenPiece = move.getPieceMoved();
            //Checking if piece exists
            if (chosenPiece == null) {
                return false;
            }
            //Checking if it is that players turn
            if (player != currentTurn) {
                return false;
            }
            //Making sure the piece belongs to the player
            if (chosenPiece.isWhite() != player.isWhiteSide()) {
                return false;
            }
            //Checks to see if the move is valid according the the pieces pattern
            if (!chosenPiece.canMove(board, move.getStart(), move.getEnd())) {
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
            if(this.currentTurn.isInCheck()){
                //If out of check
                if(!board.checkCheck(board, this.currentTurn)){
                    //Clears original check
                    this.currentTurn.setInCheck(false);
                    // store the move in a list if out of check
                    movesPlayed.add(move);
                    //Displays the board after each turn
                    displayBoard();
                    System.out.println("       Player Turn: " + this.currentTurn.getColor() + "\n");
                    // set the current turn to the other player
                    changeTurn(this.currentTurn);
                    //Set check message
                    if(board.checkCheck(board, this.currentTurn)){
                        System.out.println("\n"+this.currentTurn.getColor() + " in Check\n");
                        this.currentTurn.setInCheck(true);
                    }
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
                //Displays the board after each turn
                displayBoard();
                System.out.println("       Player Turn: " + this.currentTurn.getColor()+ "\n");
                // set the current turn to the other player
                changeTurn(this.currentTurn);
                //Set check message
                if(board.checkCheck(board, this.currentTurn)){
                    System.out.println("\n         "+this.currentTurn.getColor() + " in Check\n");
                    this.currentTurn.setInCheck(true);
                }
                return true;
            }
        } catch (Exception e){
            System.out.println("Error Creating movement");
        }
        //return successful move
        return true;
    }
}
