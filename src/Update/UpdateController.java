package Update;

import General.AController;
import General.PapaController;
import interfaces.IView;

import java.util.ArrayList;

public class UpdateController extends AController {

    private UpdateView view;

    public UpdateController(PapaController papa) {
        super(papa);
    }

    @Override
    public void setView(IView view) {
        this.view = (UpdateView) view;
    }

    @Override
    public void back() {

    }

    public void Login(){
        String Username = view.getTf_loginId();
        String Passwrod = view.getPf_loginPass();

        ArrayList<String> result = (ArrayList<String>)model.read(Username,Passwrod);

        if (result != null && result.size() > 1){
            view.setTf_newId(result.get(0));
            view.setPf_newPass(result.get(1));
            view.setTf_newFname(result.get(2));
            view.setTf_newLname(result.get(3));
            view.setTf_newAddress(result.get(4));
            view.setDp_newBirthdate(result.get(5));
            view.openUpdateLayout();
        }
        else{
            this.view.ShowAlert("Username or Password are incorrect");
        }
    }
    public void UpdateUserInfo(){

        String AlertMessage = "";
        String Username = view.getTf_newId();
        String Password = view.getpf_newPass();
        String Firstname = view.getTf_newFname();
        String Lastname = view.getTf_newLname();
        String Address = view.getTf_newAddress();
        String Birthdate = view.getDp_newBirthdate();

        if (Username.length() < 4)
            AlertMessage += "Username must be at least 4 characters\n";
        if(Password.length() < 6)
            AlertMessage += "Password must be at least 6 characters\n";
        if(Firstname.length() < 1)
            AlertMessage += "Firstname must be at least 1 characters\n";
        if(Lastname.length() < 1)
            AlertMessage += "Firstname must be at least 1 characters\n";
        if(Address.length() < 1)
            AlertMessage += "Firstname must be at least 1 characters\n";
        if(Birthdate.length() < 1)
            AlertMessage += "Birthdate should be dd/MM/yyyy format\n";


        if (AlertMessage.length() > 0)
            this.view.ShowAlert(AlertMessage);
        else {
            model.update(view.getTf_newId(), view.getpf_newPass(), view.getTf_newFname(), view.getTf_newLname(), view.getTf_newAddress(), view.getDp_newBirthdate());
            SwapScene(PapaController.Views.MainWindow);
        }
    }
}
