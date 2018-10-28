package Update;

import General.AController;
import interfaces.IView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateView implements IView {

    private UpdateController controller;

    @FXML
    private TextField tf_loginId;
    @FXML
    private PasswordField pf_loginPass;
    @FXML
    private Button btn_login;
    @FXML
    private TextField tf_newId;
    @FXML
    private PasswordField pf_newPass;
    @FXML
    private TextField tf_newFname;
    @FXML
    private TextField tf_newLname;
    @FXML
    private TextField tf_newAddress;
    @FXML
    private DatePicker dp_newBirthdate;
    @FXML
    private Button btn_update;
    @FXML
    private GridPane update_layout;


    public UpdateView(){

    }

    public String getTf_loginId() {
        return tf_loginId.getText();
    }

    public String getPf_loginPass() {
        return pf_loginPass.getText();
    }

    public String getTf_newId() {
        return tf_newId.getText();
    }

    public void setTf_newId(String tf_newId) {
        this.tf_newId.setText(tf_newId);
    }

    public String getpf_newPass() {
        return pf_newPass.getText();
    }

    public void setPf_newPass(String pf_newPass) {
        this.pf_newPass.setText(pf_newPass);
    }

    public String getTf_newFname() {
        return tf_newFname.getText();
    }

    public void setTf_newFname(String tf_newFname) {
        this.tf_newFname.setText(tf_newFname);
    }

    public String getTf_newLname() {
        return tf_newLname.getText();
    }

    public void setTf_newLname(String tf_newLname) {
        this.tf_newLname.setText(tf_newLname);
    }

    public String getTf_newAddress() {
        return tf_newAddress.getText();
    }

    public void setTf_newAddress(String tf_newAddress) {
        this.tf_newAddress.setText(tf_newAddress);
    }

    public String getDp_newBirthdate() {
        return dp_newBirthdate.getEditor().getText();
    }

    public void setDp_newBirthdate(String date) {
        this.dp_newBirthdate.setValue(LocalDate.parse(date,DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    public void openUpdateLayout(){
        this.btn_login.setDisable(true);
        this.tf_loginId.setDisable(true);
        this.pf_loginPass.setDisable(true);
        this.update_layout.setVisible(true);
        this.btn_update.setVisible(true);
    }

    @Override
    public void setController(AController controller) {
        this.controller = (UpdateController) controller;
    }

    public void Login(ActionEvent actionEvent) {
        this.controller.Login();
    }

    public void UpdateUserInfo(ActionEvent actionEvent) {
        this.controller.UpdateUserInfo();
    }
}
