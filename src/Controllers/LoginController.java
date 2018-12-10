package Controllers;

import General.PapaController;
import Models.UserModel;
import Views.LoginView;
import Views.IView;

public class LoginController extends AController {


    private LoginView view;
    private UserModel model;
    public LoginController(PapaController papa, UserModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (LoginView)view;
    }

    public void Login() {
        String Username = view.getUserName();
        String Password = view.getPassword();
        if (this.model.isUserExists(Username, Password)) {
            this.model.Signin(Username, Password);
            SwapScene(PapaController.Views.HomePageLoggedIn);
        }
        else {
            this.view.ShowAlert("User name or Password are incorrect");
        }
    }

    public void back() {
        SwapScene(PapaController.Views.HomePage);

    }
}
