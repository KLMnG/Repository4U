package General;


import interfaces.IView;
import javafx.scene.control.Button;


public abstract class AController {

private Button b_back;
protected ModelUserDB model;

public abstract void back();


    private PapaController papa;
    public AController(PapaController papa) {
        this.papa = papa;
        //this.b_back = b_back;
    }

//    public void setButtonCreateClickedHandler(EventHandler buttonCreateEventHandler){
//        b_back.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,buttonCreateEventHandler);
//    }


    protected void SwapScene(PapaController.Views viewID){
        papa.SwapScene(viewID);
    }


    public abstract void setView(IView view);

    public void setModel(ModelUserDB model) {
        this.model = model;
    }


}
