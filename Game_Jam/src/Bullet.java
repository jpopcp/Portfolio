import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bullet {

    private int row;
    private int col;

    private Picture picture;
    private boolean active = true;
    private int PADDING = 75;
    private int cellSize = 50;
    private GamePlay gamePlay;
    private int moveCounter = 0;

    private int moveDelay = 5;

    public Bullet(int col, int row, GamePlay gamePlay) {
        this.col = col;
        this.row = row;
        this.gamePlay = gamePlay;
        int x = PADDING + col * cellSize;
        int y = PADDING + row * cellSize;

        this.picture = new Picture(x, y, "resources/cuspidela.png");
        picture.draw();
    }

    public void bMove() {
        // aqui vai ter de começar outra threasd
        //Thread sleep estava a crashar copiei counter de video
        if (!active) {
            return;
        }

        moveCounter++;
        if (moveCounter >= moveDelay) {
            col++;
            picture.translate(50, 0);
            moveCounter = 0;

            if (col >= gamePlay.getField().getColumns()) {
                active = false;
                picture.delete();
            }

        /*while (col < 9 && active ) {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(50);
                    picture.translate(5, 0);
                    //shape.translate(-5, 0);
                }
            } catch (Exception ex) {
                System.out.println("tá bom.");
            }

            col += 1;

        }
        if(col == 9){
            die();
        }
         /*  while (col < 10 && active) {
               col++;
               for (Zombie zombie : gamePlay.getWaves()) {
                   if (zombie.getCol() == col && zombie.getRow() == row) {
                       zombie.attacked();
                       active = false;
                       gamePlay.bulletDie(this);
                       return;
                   }
               }
           }
           try{
               Thread.sleep(200); //move the bullets
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }*/
        }
    }
        public int getRow () {
            return row;
        }

        public int getCol () {
            return col;
        }

        public void die () {
            active = false;
            picture.delete();
        }

        public boolean isActive () {
            return active;
        }
        public void deactivate () {
            active = false;
        }
    }
