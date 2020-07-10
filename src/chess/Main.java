package chess;

public class Main {
    public static void main(String args[]){
        Game newGame = new Game();
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        newGame.initialize(p1, p2);
        //Move black rook to test
        newGame.playerMove(p1, 0, 2, 5, 7);
        //Move black rook to test
        newGame.playerMove(p2, 7, 0, 3, 0);


        newGame.listMoves();
    }
}
