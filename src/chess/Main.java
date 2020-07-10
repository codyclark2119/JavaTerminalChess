package chess;

public class Main {
    public static void main(String args[]){
        Game newGame = new Game();
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        newGame.initialize(p1, p2);
        //White bishop fail to move past White pawn
        newGame.playerMove(p1, 0, 2, 2, 0);
        //White pawn move success
        newGame.playerMove(p1, 1, 1, 3, 1);
        //Black knight move success
        newGame.playerMove(p2, 7, 1, 5, 2);
        //White Bishop move success
        newGame.playerMove(p1, 0, 2, 1, 1);
        //Black knight attack success
        newGame.playerMove(p2, 5, 2, 3, 1);
        //White bishop move fail piece in way of endpoint
        newGame.playerMove(p1, 1, 1, 7, 7);
        //White bishop successful attack
        newGame.playerMove(p1, 1, 1, 6, 6);



        newGame.listMoves();
    }
}
