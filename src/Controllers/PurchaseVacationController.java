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

//    public void confirmVacation(){
//        view.showAlert();
//    }

    public void payment(){
        view.lb_response.setVisible(true);
        String creditNumber = view.tf_creditNumber.getText();
        LocalDate date = view.d_dateExpiry.getValue();
        if(creditNumber != null && date != null){
            if (isValidCreditNumber(creditNumber) && isValidDate(date)) {
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

    private boolean isValidDate(LocalDate date) {

        int creditYear = date.getYear();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if(currentYear < creditYear) return true;
        if(currentYear > creditYear) return false;

        int creditMonth = date.getMonthValue();
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        if(currentMonth < creditMonth) return true;
        if(currentMonth > creditMonth) return false;

        int creditDay = date.getMonthValue();
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        if(currentDay < creditDay) return true;
        if(currentDay > creditDay) return false;
        return false;
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
