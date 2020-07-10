package chess;

public class Main {
    public static void main(String args[]){
        Game newGame = new Game();
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        newGame.initialize(p1, p2);
        //Move black rook to test
        newGame.playerMove(p1, 1, 1, 3, 1);
        //Move black rook to test
        newGame.playerMove(p2, 7, 1, 5, 2);
        newGame.playerMove(p1, 0, 2, 1, 1);
        newGame.playerMove(p2, 5, 2, 3, 1);
        newGame.playerMove(p1, 1, 1, 6, 6);


        newGame.listMoves();
    }
}
