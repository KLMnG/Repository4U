package CreatePackage;

import General.AController;
import General.PapaController;
import MainPackage.MainView;
import interfaces.IView;

public class CreateController extends AController {

    private CreateView view;

    public CreateController(PapaController papa) {
        super(papa);
    }

    @Override
    public void setView(IView view) {
        this.view = (CreateView) view;
    }

    public void back()
    {
        SwapScene(PapaController.Views.MainWindow);
    }

    public void b_create(){

        String Username = view.getUserName();
        if (this.model.isUserExists(Username))
            this.view.ShowAlert("Username already exists");
        else {
            model.addUser(Username, view.getPassword(), view.getBirthDate(), view.getFirstName(), view.getLastName(), view.getCity());
            SwapScene(PapaController.Views.MainWindow);
        }
    }
}
