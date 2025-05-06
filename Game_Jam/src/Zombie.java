import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Zombie {

    private int row;
    private int col;
    //private Rectangle shape;
    private Picture picture;
    private boolean alive = true;
    private int PADDING= 75;
    private int cellSize = 50;
    private int health = 3;
    private boolean blocked = false;
    private GamePlay gamePlay;
    private int moveCounter = 0;
    private int moveDelay = 10;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getHealth() {
        return health;
    }



    public Zombie(int col, int row, GamePlay gamePlay) {

        this.col = col;
        this.row = row;
        this.gamePlay = gamePlay;

        int x = PADDING + col * cellSize;
        int y = PADDING + row * cellSize;


        //precisamos do x e y qq coisa como na planta para ficar dentro dos quadrados

        //shape = new Rectangle(x,y,50,50);
        //shape.setColor(Color.RED);
        //shape.fill();
        this.picture = new Picture(x,y,"resources/zombie.png");
        picture.grow(5,5);
        picture.draw();

    }

    public void Zmove() {
        moveCounter++;

        if (moveCounter >= moveDelay) {
            col--;
            picture.translate(-50, 0);
            moveCounter = 0;
        }
        // thread sleep não funciona de forma consistente
        /*while (col > 0) {
            //if (blocked == false && alive == true) {

            try {
                    Thread.sleep(200);
                    picture.translate(-5, 0);
                    //shape.translate(-5, 0);
            } catch (Exception ex) {
                System.out.println("tá bom.");
            }
            col--;
        }*/
    }
    public void die() {

        if (picture != null) {
            picture.delete();
        }
    }
    public boolean isDead() {
        return health <= 0;
    }



    public void attacked(){
        health--;

    }
    /*public void die(){
        alive = false;
        // picture load explosion
        // tread sleep
        picture.delete();
        gamePlay.zombieDie(this);
    }*/


//metodo moveleft --rcol


    //simplegfx position that is random and have movement associated.
    //position=...
    //Associate movement to the position.
    //detects if his colliding with a plant if so ATTACK.
    //else keep moving.

    //public void attack(){
    // If X da planta == x de zombie && y da planta == y de zombie, come a planta
        /*{
            plant.setHealth(0);
        }*/
    //Else, move one square left
    // If zombie position == primeiro quadrado da linha, you lose, zombie eats brain( y <=0 )

    //Eu acho que podia estar incluido o que e
    //while collider==true attack.       atacar no metodo move enquanto se move
    //if collider == false move.         vai detetando quando detecta ataca ate nao detectar mais
    //}

    //public void gotHit(int plantDamage) {
    //setHealth(getHealth() - plantDamage);

    //if (getHealth() <= 0) {
    //  System.out.println("Zombie is dead!");
    // remove zombie from grid
}

// fazer aparecer o zombie: math.random entre 1 e 5 para o X e coluna fixa na posição 9; os zombies são gerados automaticamente de 30 em 30 segundos

