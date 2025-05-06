import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class MyKeyboard implements KeyboardHandler {

    private GamePlay gamePlay;
    private PlantCursor cursor;

    public MyKeyboard(GamePlay gamePlay, PlantCursor cursor) {
        this.gamePlay = gamePlay;
        this.cursor = cursor;

        Keyboard myKeyboard = new Keyboard(this);
        assignKeys(myKeyboard);
    }

    private void assignKeys(Keyboard myKeyboard) {
        addNewKeyboardEvent(myKeyboard, KeyboardEvent.KEY_UP, KeyboardEventType.KEY_PRESSED);
        addNewKeyboardEvent(myKeyboard, KeyboardEvent.KEY_DOWN, KeyboardEventType.KEY_PRESSED);
        addNewKeyboardEvent(myKeyboard, KeyboardEvent.KEY_RIGHT, KeyboardEventType.KEY_PRESSED);
        addNewKeyboardEvent(myKeyboard, KeyboardEvent.KEY_LEFT, KeyboardEventType.KEY_PRESSED);
        addNewKeyboardEvent(myKeyboard, KeyboardEvent.KEY_SPACE, KeyboardEventType.KEY_PRESSED);
        addNewKeyboardEvent(myKeyboard,KeyboardEvent.KEY_ENTER, KeyboardEventType.KEY_PRESSED);
    }

    private void addNewKeyboardEvent( Keyboard keyboard, int key, KeyboardEventType type){
        //setup the event
        KeyboardEvent event = new KeyboardEvent();
        //set the key to the event
        event.setKey(key);
        //set the type of event
        event.setKeyboardEventType(type);

        keyboard.addEventListener(event);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if ( keyboardEvent.getKey() == keyboardEvent.KEY_UP  ){
            cursor.updateCell(0 , -1);
        } else if ( keyboardEvent.getKey() == keyboardEvent.KEY_DOWN ){
            cursor.updateCell(0, 1);
        } else if ( keyboardEvent.getKey() == keyboardEvent.KEY_LEFT  ) {
            cursor.updateCell(-1, 0);
        } else if ( keyboardEvent.getKey() == keyboardEvent.KEY_RIGHT  ) {
            cursor.updateCell(1,0);
        } else if ( keyboardEvent.getKey() == keyboardEvent.KEY_SPACE  ) {
            if (gamePlay.isStarted() == true){
            gamePlay.placePlant(cursor.getColumn(), cursor.getRow());}
        }else if (keyboardEvent.getKey() == keyboardEvent.KEY_ENTER){
            if(gamePlay.isStarted() == false){
                gamePlay.startGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
