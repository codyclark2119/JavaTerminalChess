package chess;

public class Board {
    Space[][] spaces;

    public void resetBoard(){
        //Set Major characters
        //Set Pawns
        //Set Empty spaces
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                spaces[i][j] = new Space(i, j, null);
            }
        }
    }
}
