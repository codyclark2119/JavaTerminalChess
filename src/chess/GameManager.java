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
        newGame.startGame();
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

    public boolean confirm(){
        board.displayMessage("      Submit (y/n) to finalize your move");
        scanner.nextLine();
        String confirm = scanner.nextLine().toLowerCase();
        System.out.println(confirm);
        if (confirm.matches("y")) {
            return true;
        }
        else{
            return false;
        }
    }


    public void startGame(){
        while(!isOver()){
            this.getBoard().displayBoard(getCurrentPlayer());
            board.displayMessage("             Player Turn: " + this.getCurrentPlayer().getColor());
            System.out.println("Input piece to move X Axis number(Left to Right)");
            int startX = scanner.nextInt();
            System.out.println("Input piece to move Y Axis number(Top to Bottom)");
            int startY = scanner.nextInt();
            if(getBoard().getPiece(startY, startX) == null || getBoard().getPiece(startY, startX).isWhite() != getCurrentPlayer().isWhiteSide()){
                board.displayMessage("     No valid piece selected");
            } else{
                this.getBoard().displayBoard(getCurrentPlayer());
                board.displayMessage("        " +getBoard().getPiece(startY, startX).getColor() + " " + getBoard().getPiece(startY, startX).getName() + " Start Space: (" + startX + "," + startY + ") ");
                System.out.println("Input end space X Axis number(Left to Right)");
                int endX = scanner.nextInt();
                System.out.println("Input end space Y Axis number(Top to Bottom)");
                int endY = scanner.nextInt();
                if(validSpace(startY, startX, endY, endX)){
                    Piece originalPiece = board.getPiece(endY, endX);

                    if(playerMove(startY, startX, endY, endX)){
                        this.getBoard().displayBoard(getCurrentPlayer());
                        board.displayMessage("     Start Space: (" + startX + "," + startY + ") " + "End Space: (" + endX + "," + endY + ")");
                        if(confirm()){
                            board.displayMessage("               Successful Move!");
                            this.getBoard().displayBoard(getCurrentPlayer());
                            changeTurn(getCurrentPlayer());

                            if (getBoard().checkCheckMate(getBoard(), getCurrentPlayer(), getEnemyPlayer())){
                                board.displayMessage("True checkmate");
                            }
                            //Set check message
                            if(getBoard().checkCheck(getBoard(), getCurrentPlayer())){
                                board.displayMessage(getCurrentPlayer().getColor() + " in Check");
                                getCurrentPlayer().setInCheck(true);
                            }
                        } else {
                            board.getBox(startY, startX).setPiece(board.getBox(endY, endX).getPiece() );
                            board.getBox(endY, endX).setPiece(originalPiece);
                            board.displayMessage("Move Cancelled");
                        }
                    } else {
                        board.displayMessage("Unsuccessful Move");
                    }
                } else {
                    board.displayMessage("Unsuccessful Move");
                }
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

