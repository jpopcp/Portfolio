
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class PlantCursor {

    private Picture cursor;

    private Field field;

    private int cellSize = 50;

    private int PADDING = 75;
    private int column =0;
    private int row= 0;

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public PlantCursor (Field field) {
        this.field = field;
        int x = PADDING + column * cellSize;
        int y = PADDING + row * cellSize;
        cursor = new Picture( x, y,"resources/crosshairs.png");
        cursor.draw();

    }

    public void updateCell( int plusColumn, int plusRow){
        int newCol =  column + plusColumn;
        int newRow = row + plusRow;
        if (newCol < 0 || newCol >= 9 || newRow < 0 || newRow >= 5) {
            return;
        }
        column = newCol;
        row = newRow;
        int x = PADDING + column * cellSize;
        int y = PADDING + row * cellSize;
        cursor.translate(x -cursor.getX(), y - cursor.getY());
    }


}

