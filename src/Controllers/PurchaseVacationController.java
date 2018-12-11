package Controllers;

import General.PapaController;
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
        SwapScene(PapaController.Views.HomePage);
    }

    public void showVacationInfo(){
        //List<String> data = model.read()//need to insert all the codes here !
    }


    public void payment(){
        view.lb_response.setVisible(true);
        String creditNumber = view.tf_creditNumber.getText();
        String creditMonth = (String) view.cb_month.getValue();
        String creditYear = (String) view.cb_year.getValue();

        if(creditNumber != null && creditMonth != null && creditYear != null){
            if (isValidCreditNumber(creditNumber) && creditNumber.length() == 16) {
                model.addPayment(creditNumber,creditMonth+"-"+creditYear);
                view.lb_response.setText("Payment success !");
                view.lb_response.setStyle("-fx-text-fill: green;");
            }else{
                view.lb_response.setText("Wrong Parameters");
                view.lb_response.setStyle("-fx-text-fill: red;");
            }
        }else{
            view.lb_response.setText("Empty Parameters");
            view.lb_response.setStyle("-fx-text-fill: red;");
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
}
