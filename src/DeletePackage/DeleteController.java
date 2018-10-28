package DeletePackage;



import General.AController;
import General.PapaController;
import interfaces.IView;

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
            model.delete(view.userTxt.getText(),view.passTxt.getText());
        }
        catch (Exception e){
            view.showError();
        }

    }

    @Override
    public void back() {

    }
}
