import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;


public class Field {

    private int PADDING = 75;
    private int cellSize = 50;
    private int columns= 9;
    private int rows = 5;
    private int [][] field1;

    private int width = columns * cellSize;
    private int height = rows * cellSize;
    private Picture background;

    public Field(){

        drawWindow();
        field1 = new int[columns][rows];
        drawField();
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    private void drawField(){
        //Rectangle field = new Rectangle(PADDING,PADDING,width,height);
        //Picture fieldVisual = new Picture(PADDING, PADDING,"resources/field.png");
        //field.draw();
        //fieldVisual.draw();

        for(int row = 0;row < rows; row++){
            for(int column = 0; column <columns; column++){
                int x = PADDING + column * cellSize;
                int y = PADDING + row * cellSize;
                Rectangle cell = new Rectangle(x , y ,cellSize,cellSize);
                cell.draw();
            }
        }
    }

    private void drawWindow(){
        background = new Picture(0,0,"resources/img_6.png");
        background.draw();
        Rectangle window = new Rectangle(0, 0, 525 ,400);
        window.draw();
    }
    public boolean isEmpty(int col, int row){
        return field1[col][row] == 0;
    }
    public void placeLama(int col, int row){
        field1[col][row] = 1;
    }
    public void deleteLama(int col, int row){
         field1[col][row] = 0;
    }
    public void placeZ(int col, int row){
        field1[col][row]= 2;
    }
    public void removeZ(int col, int row){
        field1[col][row]=0;
    }

}
