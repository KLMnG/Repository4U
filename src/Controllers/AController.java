package Controllers;


import General.PapaController;
import Models.UserModel;
import Views.IView;


public abstract class AController {

protected UserModel model;

    private PapaController papa;
    public AController(PapaController papa) {
        this.papa = papa;
    }


    protected void SwapScene(PapaController.Views viewID){
        papa.SwapScene(viewID);
    }

    public abstract void setView(IView view);

}
