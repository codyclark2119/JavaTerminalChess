package chess;

public class Main {
    public static void main(String args[]){
        Game newGame = new Game();
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        newGame.initialize(p1, p2);
        //Test Successful move
        newGame.playerMove(p1, 1, 0, 2, 0);
        //Test Out of turn move
        newGame.playerMove(p1, 2, 0, 3, 0);
        //Test Moving pawns backwards fail
        newGame.playerMove(p2, 6, 0, 7, 0);
        //Test attack fail
        newGame.playerMove(p2, 6, 0, 5, 1);


        newGame.listMoves();
    }
}
