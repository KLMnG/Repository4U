package DeletePackage;



import Controllers.AController;
import General.PapaController;
import Views.IView;

public class DeleteController extends AController {
    private DeleteView view;

    public DeleteController(PapaController papa) {
        super(papa);
    }

    @Override
    public void setView(IView view) {
        this.view = (DeleteView) view;
    }

    public void deleteUser(){

        try {
            String Username = view.userTxt.getText();
            String Password = view.passTxt.getText();
            if (model.isUserExists(Username,Password)) {
                model.delete(Username, Password);
                //SwapScene(PapaController.Views.MainWindow);
            }
            else {
                this.view.ShowAlert("Username or Password are incorrect");
            }
        }
        catch (Exception e){
            view.showError();
        }

    }

    public void back() {
        //SwapScene(PapaController.Views.MainWindow);
    }
}
