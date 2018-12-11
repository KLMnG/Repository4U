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

    public void initializeView() {

    }
    @Override
    public void setView(IView view) {
        this.view = (PurchaseVacationView)view;
    }

    public void back() {
        SwapScene(PapaController.Views.MassegesRequests);
    }

    public void payment(){
       // view.lb_response.setVisible(true);
        String creditNumber = view.tf_creditNumber.getText();
        String creditMonth = (String) view.cb_month.getValue();
        String creditYear = (String) view.cb_year.getValue();

        if(creditNumber != null && creditMonth != null && creditYear != null){
            if (isValidCreditNumber(creditNumber) && creditNumber.length() == 16) {
                model.addPayment(creditNumber,creditMonth+"-"+creditYear);
                view.showAlert();
                model.removeRequest(model.getSeller(), model.getBuyer(), model.getVacationCode());
                SwapScene(PapaController.Views.MassegesRequests);
            }else{
                view.showWarning("Invalid credit card number");
            }
        }else{
            view.showWarning("Empty parameters");
        }
    }

    private boolean isValidCreditNumber(String creditNumber) {
        for(int i=0;i<creditNumber.length();i++){
            if(!Character.isDigit(creditNumber.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public void initialize() {
        view.cb_year.getItems().add("2019");
        view.cb_year.getItems().add("2020");
        view.cb_year.getItems().add("2021");
        view.cb_year.getItems().add("2022");
        view.cb_year.getItems().add("2023");
        view.cb_year.getItems().add("2024");
        view.cb_month.getItems().add("01");
        view.cb_month.getItems().add("02");
        view.cb_month.getItems().add("03");
        view.cb_month.getItems().add("04");
        view.cb_month.getItems().add("05");
        view.cb_month.getItems().add("06");
        view.cb_month.getItems().add("07");
        view.cb_month.getItems().add("08");
        view.cb_month.getItems().add("09");
        view.cb_month.getItems().add("10");
        view.cb_month.getItems().add("11");
        view.cb_month.getItems().add("12");

    }
}
