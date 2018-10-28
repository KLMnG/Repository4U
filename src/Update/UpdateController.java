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

        if (result.size() > 1){
            view.setTf_newId(result.get(0));
            view.setPf_newPass(result.get(1));
            view.setTf_newFname(result.get(2));
            view.setTf_newLname(result.get(3));
            view.setTf_newAddress(result.get(4));
            view.setDp_newBirthdate(result.get(5));
            view.openUpdateLayout();
        }
    }
    public void UpdateUserInfo(){
        model.update(view.getTf_newId(),view.getpf_newPass(),view.getTf_newFname(),view.getTf_newLname(),view.getTf_newAddress(),view.getDp_newBirthdate());
        SwapScene(PapaController.Views.MainWindow);
    }
}
