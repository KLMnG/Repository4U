package ReadPackage;

import Controllers.AController;
import General.PapaController;
import Views.IView;
import java.util.List;

public class ReadController extends AController {

    private String userName;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userAddress;
    private String userBirthDate;

    private ReadView view;

    public ReadController(PapaController papa) {
        super(papa);
    }

    @Override
    public void setView(IView view) {
        this.view = (ReadView) view;
    }

    public void SearchUser() {
        List userInfo = model.read(view.getUserNameFromTextField());
        if(userInfo != null && userInfo.size() != 0) {
            separationInfo(userInfo);
            view.setVisibleTrue();
            view.setUserName(userName);
            view.setUserPassword(userPassword);
            view.setUserFirstName(userFirstName);
            view.setUserLastName(userLastName);
            view.setUserAddress(userAddress);
            view.setUserBirthDate(userBirthDate);
        }
        else{
            view.setVisibleFalse();
        }
    }
    private void separationInfo(List userInfo) {
        userName = (String) userInfo.get(0);
        userPassword = (String) userInfo.get(1);
        userFirstName = (String) userInfo.get(2);
        userLastName = (String) userInfo.get(3);
        userAddress = (String) userInfo.get(4);
        userBirthDate = (String) userInfo.get(5);
    }

    public void back() {
        //SwapScene(PapaController.Views.MainWindow);
    }

}
