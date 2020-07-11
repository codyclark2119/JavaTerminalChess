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
        newGame.playerMove(p2, 6, 2, 4, 2);
        //White Queen move check fail
        newGame.playerMove(p1, 0, 4, 3, 1);
        //Black pawn move
        newGame.playerMove(p2, 6, 5, 4, 5);
        //White Queen move check success
        newGame.playerMove(p1, 3, 1, 4, 0);
        //Black queen move
        newGame.playerMove(p2, 7, 4, 4, 7);
        //White pawn for queen successful check
        newGame.playerMove(p1, 1, 4, 3, 4);
        //Black pawn for white check block
        newGame.playerMove(p2, 6, 1, 5, 1);
        //white queen check fail
        newGame.playerMove(p1, 4, 0, 5, 0);

        newGame.listMoves();
    }
}
