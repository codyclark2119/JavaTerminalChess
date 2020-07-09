package chess;

public class Main {
    public static void main(String args[]){
        Game newGame = new Game();
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        newGame.initialize(p1, p2);
        //Move white knight
        newGame.playerMove(p1, 0, 1, 2, 0);
        //Move black pawn
        newGame.playerMove(p2, 6, 0, 4, 0);
        //Move white pawn
        newGame.playerMove(p1, 1, 1, 2, 1);
        //Move black pawn to kill
        newGame.playerMove(p2, 6, 1, 4, 1);
        //Move white knight to attack
        newGame.playerMove(p1, 2, 0, 4, 1);

        newGame.listMoves();
    }
}
