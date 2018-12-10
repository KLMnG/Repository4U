package Controllers;

import Models.UserModel;
import Views.RegisterView;
import General.PapaController;
import Views.IView;

public class RegisterController extends AController {

    private RegisterView view;
    private UserModel model;

    public RegisterController(PapaController papa, UserModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (RegisterView) view;
    }

    public void back()
    {
        SwapScene(PapaController.Views.HomePage);
    }

    public void b_create(){

        String Username = view.getUserName();
        if (this.model.isUserExists(Username))
            this.view.ShowAlert("Username already exists");
        else {
            model.addUser(Username, view.getPassword(), view.getBirthDate(), view.getFirstName(), view.getLastName(), view.getCity());
            SwapScene(PapaController.Views.HomePage);
        }
    }
}
