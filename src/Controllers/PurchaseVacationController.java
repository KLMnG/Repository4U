package Controllers;

import General.PapaController;
import General.VacationData;
import Models.PurchaseVacationModel;
import Views.IView;
import Views.PurchaseVacationView;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;


public class PurchaseVacationController extends AController {

    private PurchaseVacationView view;
    private PurchaseVacationModel model;

    public PurchaseVacationController(PapaController papa, PurchaseVacationModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (PurchaseVacationView)view;
    }

    public void back() {
        SwapScene(PapaController.Views.MassegesRequests);
    }

    public void gotMoney(){
        view.showWarning("The purchase completed");
        model.removeRequest(model.getSeller(), model.getBuyer(), model.getVacationCode());
        SwapScene(PapaController.Views.MassegesRequests);
    }
}
