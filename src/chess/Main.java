package chess;

public class Main {
    public static void main(String args[]){
        Game newGame = new Game();
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        newGame.initialize(p1, p2);
        //White pawn move success
        newGame.playerMove(p1, 1, 3, 3, 3);
        //Black pawn move success
        newGame.playerMove(p2, 6, 0, 4, 0);
        //White queen move diagonal success
        newGame.playerMove(p1, 0, 4, 4, 0);
        //Black pawn move success
        newGame.playerMove(p2, 6, 1, 4, 1);
        //White queen move horizontal success
        newGame.playerMove(p1, 4, 0, 2, 0);
        newGame.listMoves();
    }
}
