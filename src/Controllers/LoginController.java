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
}
