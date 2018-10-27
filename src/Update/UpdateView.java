package Update;

import interfaces.IView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateView implements IView {


    private Scene m_Scene;

    @FXML
    private TextField tf_loginId;
    private PasswordField pf_loginPass;
    private Button btn_login;
    private TextField tf_newId;
    private PasswordField pf_newPass;
    private TextField tf_newFname;
    private TextField tf_newLname;
    private TextField tf_newAddress;
    private DatePicker dp_newBirthdate;
    private Button btn_update;
    private GridPane update_layout;



    public UpdateView(){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("UpdateFXML.fxml"));
            this.tf_loginId = (TextField) root.lookup("#tf_loginId");
            this.pf_loginPass = (PasswordField) root.lookup("#pf_loginPass");
            this.btn_login = (Button) root.lookup("#btn_login");
            this.tf_newId = (TextField) root.lookup("#tf_newId");
            this.pf_newPass = (PasswordField) root.lookup("#pf_newPass");
            this.tf_newFname = (TextField) root.lookup("#tf_newFname");
            this.tf_newLname = (TextField) root.lookup("#tf_newLname");
            this.tf_newAddress = (TextField) root.lookup("#tf_newAddress");
            this.dp_newBirthdate = (DatePicker) root.lookup("#dp_newBirthdate");
            this.btn_update = (Button) root.lookup("#btn_update");
            this.update_layout = (GridPane) root.lookup("#update_layout");
            this.m_Scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }



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
    public Scene getScene() {
        return this.m_Scene;
    }
}
