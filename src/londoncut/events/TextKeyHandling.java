
package londoncut.events;

import javafx.scene.input.KeyEvent;


public class TextKeyHandling {
    
    public static void onlyCharacters(KeyEvent event){
        String data=event.getCharacter();
        char charAt=data.charAt(0);
        if(Character.isDigit(charAt)){
            event.consume();
        }
    }
    
    public static void onlyNumbers(KeyEvent event){
        String data=event.getCharacter();
        char charAt=data.charAt(0);
        if(!Character.isDigit(charAt)){
            event.consume();
        }
    }
    
    public static void noEdit(KeyEvent event){
        String data=event.getCharacter();
        char charAt=data.charAt(0);
        if(!Character.isDigit(charAt)){
            event.consume();
        }
    }
}