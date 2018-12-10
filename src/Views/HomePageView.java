package Views;

import Controllers.AController;
import Controllers.HomePageController;
import General.HotelData;
import General.NightStayData;
import General.TicketData;
import General.VacationData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class HomePageView implements IView{

    public TableView tv_vacations;
    public TableColumn col_from;
    public TableColumn col_to;
    public TableColumn col_days;
    public TableColumn col_depart;
    public TableColumn col_travelers;
    public TableColumn col_price;

    private HomePageController controller;


    public void initialize(){

        ObservableList<VacationData> data = FXCollections.observableArrayList();

//        List<TicketData> lst_t = new ArrayList<>();
//        HotelData h = new HotelData("12","234","234");
//        NightStayData n1 = new NightStayData(3,4,h);
//        NightStayData n2 = new NightStayData(3,7,h);
//        NightStayData n3 = new NightStayData(3,10,h);
//        lst_t.add(new TicketData("1","1","1","1",null,"1",null));
//        lst_t.add(new TicketData("1","1","1","1",null,"1",null));
//        lst_t.add(new TicketData("1","1","1","1",null,"1",null));
//
//        data.add(new VacationData(lst_t,n1,500,"23"));
//        data.add(new VacationData(lst_t,n2,6000,"23"));
//        data.add(new VacationData(lst_t,n3,300,"23"));

        col_from.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("From")
        );
        col_to.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("To")
        );
        col_days.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Days")
        );
        col_depart.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Depart")
        );
        col_travelers.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Travelers")
        );
        col_price.setCellValueFactory(
                new PropertyValueFactory<VacationData,Integer>("Price")
        );

        tv_vacations.setItems(data);
    }

    private void initializeView() {

        this.controller.getVacations();
    }

    public void Signin(ActionEvent actionEvent) {
        this.controller.openLoginWindow();
    }

    public void Signup(ActionEvent actionEvent) {
        this.controller.openRegisterWindow();
    }
    @Override
    public void setController(AController controller) {
        this.controller = (HomePageController) controller;
        initializeView();
    }

    public void addToTable(List<String[]> listVac){

     
    }



}
