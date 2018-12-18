package General;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PurchaseMessage {


    private final SimpleStringProperty Seller_User;
    private final SimpleStringProperty Purchase_User;
    private final SimpleIntegerProperty VacationCode;
    private String Date;


    public PurchaseMessage(String seller_User, String purchase_User, int vacationCode, String Date) {
        this.Seller_User = new SimpleStringProperty(seller_User);
        this.Purchase_User = new SimpleStringProperty(purchase_User);
        this.VacationCode = new SimpleIntegerProperty(vacationCode);
        this.Date = Date;
    }

    public String getSeller_User() {
        return Seller_User.get();
    }

    public void setSeller_User(String seller_User) {
        this.Seller_User.set(seller_User);
    }

    public String getPurchase_User() {
        return Purchase_User.get();
    }

    public void setPurchase_User(String purchase_User) {
        this.Purchase_User.set(purchase_User);
    }

    public int getVacationCode() {
        return VacationCode.get();
    }

    public void setVacationCode(int vacationCode) {
        this.VacationCode.set(vacationCode);
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date){
        this.Date = date;
    }

}
