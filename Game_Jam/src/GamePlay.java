import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;
import java.util.List;

public class GamePlay {
    private Field field;
    //private int [][] map;
    private List<Lama> lamas;
    private int thisColumn = 0;
    private int thisRow = 0;
    private PlantCursor plantCursor;
    private boolean gameOver = false;
    private List<Zombie> waves;
    private List<Bullet> bullets;
    //private int waveCounter = 0;
    private int money = 100;
    private Picture picture;
    private Coin coin = new Coin(70,200,8);
    private boolean started= false;
    private Text money1;

    public GamePlay() {
        this.field = new Field();
        this.lamas = new ArrayList<>();
        this.bullets = new ArrayList<>();
        // criar o array de zombies aqui?
        this.plantCursor = new PlantCursor(field);
        new MyKeyboard(this, plantCursor);
        this.waves = new ArrayList<>();
        money1 = new Text(140, 45, String.valueOf(money) );
        money1.grow(20,20);
        money1.draw();
        coin.show();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Field getField() {
        return field;
    }

    public List<Lama> getLamas() {
        return lamas;
    }

    public List<Zombie> getWaves() {
        return waves;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    /*public void moveCursor(int plusColumn, int plusRow) {

        int newColumn = this.thisColumn + plusColumn ;
        int newRow = this.thisRow + plusRow;

        if (newColumn >= 0 && newColumn < 9 && newRow >= 0 && newRow < 5) {

            this.thisColumn = newColumn;
            this.thisRow = newRow;

            plantCursor.updateCell(this.thisColumn, this.thisRow);
        }
    }*/

    public void placePlant(int thisColumn, int thisRow) {
        if(!field.isEmpty(thisColumn, thisRow)){
            return;
        }
        int cost = 50;
        if (money < cost) {
            System.out.println("no money no funny");
            return;
        }
        //money = -25
        money-=50;
        Lama lama = new Lama(thisColumn, thisRow, this);
        lamas.add(lama);
        field.placeLama(thisColumn, thisRow);

        //adicionar aqui o lama do dinheiro



    }

    public void testeBullet(Bullet bull){
        //Bullet bullet = new Bullet(selectedCol+1,selectedRow);
        bullets.add(bull);
    }




    public void Wave() {

        // resolver criação de zombie após teclado/grelha funcionar
        new Thread(() -> {
            while (!gameOver) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("deu caca na wave");
                }
                int row = (int) Math.floor(Math.random() * 5);

                Zombie z = new Zombie(8, row, this);
                waves.add(z);
                field.placeZ(8, row);
            }
        }).start();
        System.out.println("olá sr Zumbie.");

    }


    public void checkCollisions() {

        List<Zombie> zombiesWhoDie = new ArrayList<>();
        List<Lama> lamasWhoDie = new ArrayList<>();
        List<Bullet> bulletsWhoDie = new ArrayList<>();

        for (Zombie zombie : waves) {
            for (Lama lama : lamas) {
                if (lama.getCol() +1 == zombie.getCol() && lama.getRow() == zombie.getRow()) {
                    /*
                    zombie.stop();
                    try {
                       Thread.sleep(500);
                    } catch (Exception ex) {
                        System.out.println("pausa para morrer");
                    }*/
                    lama.die();

                    lamasWhoDie.add(lama);
                    //zombie.starMoving();
                }

            }
        }

        for (Bullet bullet : bullets) {
            for (Zombie zombie : waves) {
                if (bullet.getCol() == zombie.getCol() && bullet.getRow() == zombie.getRow() || bullet.getCol() -1 == zombie.getCol() && bullet.getRow() == zombie.getRow() ) {

                    zombie.attacked();
                    //bullet.die();
                    bulletsWhoDie.add(bullet);

                    if (zombie.getHealth() <= 0) {
                        zombie.die();
                        //bullet.die();
                        money += 25;
                        zombiesWhoDie.add(zombie);
                        //bulletsWhoDie.add(bullet);
                        System.out.println("MONEY: " + money);

                    }
                }

                // espera 2 segundos e faz delete do lama

            }

        }
        for (Zombie zombie1 : zombiesWhoDie) {
            waves.remove(zombie1);
            field.removeZ(zombie1.getCol(), zombie1.getRow());
            zombie1.die();
        }
        for (Lama lama : lamasWhoDie) {
            lamas.remove(lama);
            field.deleteLama(lama.getCol(), lama.getRow());
            lama.die();
        }
        for (Bullet bullet1 : bulletsWhoDie) {
            bullets.remove(bullet1);
            bullet1.die();
        }
    }




    private void updateGame() {
        for (Zombie z : waves) {
            z.Zmove();
            if (z.getCol() < 0) {
                System.out.println("ai que susto");
                        gameOver = true;
                        picture.load("resources/img_2.png");
                        picture.draw();
                return;
            }
        }
        for (Bullet b : bullets) {
            b.bMove();
        }
        money1.setText(String.valueOf(money) );
    }
    /*public void bulletDie(Bullet bullet) {
        bullets.remove(bullet);
    }*/
    public void startGameMenu(){
          picture = new Picture(0,0 ,"resources/img.png");
          picture.draw();
    }
    public void startGame() {

        gameStarted();
        picture.delete();

        new Thread(() -> {
            while (!gameOver) {
                updateGame();
                checkCollisions();
                try {
                    Thread.sleep(150);
                } catch (Exception e) {
                    System.out.println("no start game");;
                }
            }
            System.out.println("Game Over!");
            
        }).start();
        Wave();  
    }
    public void gameStarted(){
         started = true;
    }

    public boolean isStarted() {
        return started;
    }
}
