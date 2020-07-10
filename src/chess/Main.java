package chess;

public class Main {
    public static void main(String args[]){
        Game newGame = new Game();
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        newGame.initialize(p1, p2);
        //Move black rook to test
        newGame.playerMove(p1, 0, 2, 2, 0);
        //Move black rook to test
        newGame.playerMove(p2, 7, 2, 5, 0);
        newGame.playerMove(p1, 0, 5, 2, 7);
        newGame.playerMove(p2, 7, 5, 5, 7);


        newGame.listMoves();
    }
}
