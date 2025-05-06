public class DalaiLama {

    private int col;
    private int row;
    private GamePlay gamePlay;
    private boolean isAlive = true;

    public DalaiLama(int row, int col,GamePlay gamePlay) {
        this.row = row;
        this.col = col;
        this.gamePlay = gamePlay;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    public void die() {
        isAlive = false;
    }

}
