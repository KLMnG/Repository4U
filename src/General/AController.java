package General;


import interfaces.IView;
import javafx.scene.control.Button;


public abstract class AController {

protected ModelUserDB model;

    private PapaController papa;
    public AController(PapaController papa) {
        this.papa = papa;
    }


    protected void SwapScene(PapaController.Views viewID){
        papa.SwapScene(viewID);
    }


    public abstract void setView(IView view);

    public void setModel(ModelUserDB model) {
        this.model = model;
    }


}
