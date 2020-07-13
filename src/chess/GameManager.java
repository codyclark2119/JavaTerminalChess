package chess;

import java.util.Scanner;

public class GameManager {
    private static boolean isOver = false;
    private static boolean moveIsOver = false;
    private static Game newGame;
    private static Board board;
    private static Player p1 = new Player(true, true);
    private static Player p2 = new Player(false, false);
    private static Player[] players = new Player[]{p1, p2};
    private static Scanner scanner = new Scanner(System.in);
    private static Player currentPlayer = p1;

    public static void main(String args[]){
        GameManager newGame = new GameManager();
        while(!isOver){
            newGame.startGame();
        }
    }

    public GameManager() {
        this.setGame(new Game());
        this.setBoard(new Board());
        board.displayMessage("               Welcome to Chess");
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public boolean isMoveIsOver() {
        return moveIsOver;
    }

    public void setMoveIsOver(boolean moveIsOver) {
        this.moveIsOver = moveIsOver;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getEnemyPlayer() {
        if (currentPlayer == players[0]) {
            return players[1];
        } else {
            return players[0];
        }
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void changeTurn(Player player){
        // set the current turn to the other player
        if (player == players[0]) {
            players[0].setCurrentTurn(false);
            setCurrentPlayer(players[1]);
            players[1].setCurrentTurn(true);
        } else {
            players[1].setCurrentTurn(false);
            setCurrentPlayer(players[0]);
            players[0].setCurrentTurn(true);
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public static Game getGame() {
        return newGame;
    }

    public static void setGame(Game newGame) {
        GameManager.newGame = newGame;
    }

    public boolean validSpace(int sy, int sx, int ey, int ex){
        int[] vals = new int[]{sy, sx, ey, ex};
        for(int i = 0; i < vals.length; i++){
            if ((vals[i] < 0 || vals[i] > 7) || ((sx == ex) &&  (sy == ey))) {
                System.out.println("Invalid space Selection");
                return false;
            }
        }
        return true;
    }

    public int askForInput(String question){
        System.out.println(question);
        return scanner.nextInt();
    }

    public boolean confirm(String msg){
        scanner.nextLine();
        board.displayMessage(msg);
        String confirm = scanner.nextLine().toLowerCase();
        System.out.println(confirm);
        if (confirm.matches("y")) {
            return true;
        }
        else{
            return false;
        }
    }

    public static Game getNewGame() {
        return newGame;
    }

    public void playerMoveInput(){
        //Reset move over to false
        setMoveIsOver(false);
        //while the move isn't over
        while(!isMoveIsOver()){
            if(board.checkCheckMate(board, getCurrentPlayer(), getEnemyPlayer())){
                setMoveIsOver(true);
                setOver(true);
            }
            //Show board
            this.getBoard().displayBoard(getCurrentPlayer());
            board.displayMessage("             Player Turn: " + this.getCurrentPlayer().getColor());
            //Asking for user input for starting piece to be chosen
            int startX = askForInput("Starting space's X Axis: ");
            int startY = askForInput("Starting space's Y Axis: ");
            Piece currentPiece = getBoard().getPiece(startY, startX);
            //Check if piece isn't valid
            if(currentPiece == null || currentPiece.isWhite() != getCurrentPlayer().isWhiteSide()){
                board.displayMessage("     No valid piece selected");
            } else {
                //Show board again
                this.getBoard().displayBoard(getCurrentPlayer());
                board.displayMessage("        " +getBoard().getPiece(startY, startX).getColor() + " " + getBoard().getPiece(startY, startX).getName() + " Start Space: (" + startX + "," + startY + ") ");
                //Asking for user input for end space to be chosen
                int endX = askForInput("End space's X Axis: ");
                int endY = askForInput("End space's Y Axis: ");
                //Check if points chosen are both valid spaces
                if(validSpace(startY, startX, endY, endX)){
                    //Grabs original piece of end space in case of confirm refusal
                    Piece originalPiece = board.getPiece(endY, endX);
                    //If the move is allowed by the pieces rules
                    if(!playerMove(startY, startX, endY, endX)){
                        //Move fails, invalidate move
                        board.displayMessage("            Move not allowed");
                        board.getBox(startY, startX).setPiece(currentPiece);
                        board.getBox(endY, endX).setPiece(originalPiece);
                        currentPiece.setX(startX);
                        currentPiece.setY(startY);
                        if(originalPiece != null){
                            originalPiece.setX(endX);
                            originalPiece.setY(endY);
                        }
                    } else {
                        //Show preview of move
                        this.getBoard().displayBoard(getCurrentPlayer());
                        board.displayMessage("     Start Space: (" + startX + "," + startY + ") " + "End Space: (" + endX + "," + endY + ")");
                        //If user confirms yes to the move
                        if(confirm("      Submit (y/n) to finalize your move")){
                            //Move success
                            board.displayMessage("               Successful Move!");
                            this.getBoard().displayBoard(getCurrentPlayer());
                            //Change turn
                            changeTurn(getCurrentPlayer());
                            //Ends current move while lops
                        } else {
                            board.getBox(startY, startX).setPiece(currentPiece);
                            board.getBox(endY, endX).setPiece(originalPiece);
                            currentPiece.setX(startY);
                            currentPiece.setY(startX);
                            if(originalPiece != null){
                                originalPiece.setX(endY);
                                originalPiece.setY(endX);
                            }
                            board.displayMessage("             Move Cancelled for " + board.getBox(startY, startX).getPiece().getName());
                        }
                        setMoveIsOver(true);

                    }
                } else {
                    board.displayMessage("Unsuccessful Move");
                }
            }
        }
    }


    public void startGame(){
        playerMoveInput();
        if(isOver()){
            if(confirm("      Would you like to play again?(y/n)")){
                setOver(false);
                startGame();
            } else{
                board.displayMessage("      Thank you for playing");
                System.exit(0);
            }
        }
    }

    public boolean playerMove(int startX, int startY, int endX, int endY) {
        //Getting the start space from the board
        Space startBox = getBoard().getBox(startX, startY);
        //Getting the space to move to from the board
        Space endBox = getBoard().getBox(endX, endY);
        //Creating a move to send to the make move function
        Move move = new Move(getCurrentPlayer(), startBox, endBox);

        return getGame().makeMove(getBoard(), move, getCurrentPlayer());
    }
}

