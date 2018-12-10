package Controllers;

import General.PapaController;
import Models.VacationInfoLoggedinModel;
import Views.IView;
import Views.VacationInfoLoggedinView;

public class VacationInfoLoggedinController extends AController{


    private VacationInfoLoggedinView view;
    private VacationInfoLoggedinModel model;

    public VacationInfoLoggedinController(PapaController papa,VacationInfoLoggedinModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (VacationInfoLoggedinView) view;
    }

    public void initializeView() {
    }

    public void PurchaseVacation() {
        this.model.AskToPurchase();
    }

    public void back()
    {
        SwapScene(PapaController.Views.HomePageLoggedIn);
    }
}
