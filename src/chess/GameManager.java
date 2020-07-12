package chess;

import java.util.Scanner;

public class GameManager {
    private static boolean isOver;
    private static boolean moveIsOver;
    private static Game newGame;
    private static Board board;
    private static Player p1 = new Player(true, true);
    private static Player p2 = new Player(false, false);
    private static Player[] players = new Player[]{p1, p2};
    private static Scanner scanner = new Scanner(System.in);
    private static Player currentPlayer;

    public GameManager() {
        this.setGame(new Game());
        this.setBoard(new Board());
        getBoard().resetBoard();
        setOver(false);
        setMoveIsOver(false);
        if(p1.isWhiteSide()){
            setCurrentPlayer(p1);
        } else {
            setCurrentPlayer(p2);
        }
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

    public void startGame(){
        while(!isOver()){
            this.getBoard().displayBoard();
            System.out.println("Input Start Piece X Axis (Left to Right)");
            int startX = scanner.nextInt();
            System.out.println("Input Start Piece Y Axis (Top to Bottom)");
            int startY = scanner.nextInt();
            if(getBoard().getPiece(startY, startX) == null || getBoard().getPiece(startY, startX).isWhite() != getCurrentPlayer().isWhiteSide()){
                System.out.println("No valid piece selected");
            } else{
                System.out.println(getBoard().getPiece(startY, startX).getColor() + " " + getBoard().getPiece(startY, startX).getName());
            }
            System.out.println("Input End Space X Axis (Left to Right)");
            int endX = scanner.nextInt();
            System.out.println("Input End Space Y Axis (Top to Bottom)");
            int endY = scanner.nextInt();
            System.out.println("Start: (" + startX + "," + startY + ")");
            System.out.println("End: (" + endX + "," + endY + ")");
            if(playerMove(startY, startX, endY, endX)){
                System.out.println("Successful Move!");
                changeTurn(getCurrentPlayer());

                if (getBoard().checkCheckMate(getBoard(), getCurrentPlayer())){
                    System.out.println("True checkmate");
                }
                //Set check message
                if(getBoard().checkCheck(getBoard(), getCurrentPlayer())){
                    System.out.println("\n         "+getCurrentPlayer().getColor() + " in Check\n");
                    getCurrentPlayer().setInCheck(true);
                }
            } else {
                System.out.println("Unsuccessful Move");
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

