import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Timer;
import java.util.TimerTask;

public class Lama extends DalaiLama {
        //private Rectangle plant;

        private Picture picture;
        private static final int cellSize = 50;
        private static final int PADDING = 75;
        private int col;
        private int row;
        private boolean isAlive= true;
        private int health = 1;
        private Bullet bullet;
        private GamePlay gamePlay;

        public Lama(int col, int row,GamePlay gamePlay) {
            super(col, row, gamePlay);
            this.gamePlay = gamePlay;
            int x = PADDING + col * cellSize;
            int y = PADDING + row * cellSize;

           /* plant = new Rectangle(x, y, cellSize, cellSize);
            plant.setColor(Color.GREEN);
            plant.fill();*/
            this.col =col;
            this.row = row;
            picture = new Picture(x, y, "resources/Lama.png");
            picture.draw();
            shootLoop();
        }


    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getHealth() {
        return health;
    }



    public void die(){
        isAlive = false;
        picture.delete();
    }
    // Shoot in loop?
        // começa a disparar quando nasce
    private void shootLoop() {
        new Thread(() -> {
            while (isAlive && !gamePlay.isGameOver()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("odeio jogos");
                }
                if (isAlive) {
                    gamePlay.testeBullet(new Bullet(col, row, gamePlay));
                }
            }
        }).start();
    }

    /*public void shoot(){
            Bullet bullet = new Bullet(col,row, gamePlay);
            bullet.bMove();


    }*/

}

