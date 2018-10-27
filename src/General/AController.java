package General;


import javafx.event.EventHandler;
import javafx.scene.control.Button;


public abstract class AController {

private Button b_back;
public  abstract void back();


//    public AController(Button b_back) {
//        this.b_back = b_back;
//    }

    public void setButtonCreateClickedHandler(EventHandler buttonCreateEventHandler){
        b_back.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,buttonCreateEventHandler);
    }

}
