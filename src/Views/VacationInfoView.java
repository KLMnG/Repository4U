package Views;

import Controllers.AController;
import Controllers.VacationInfoController;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;

public class VacationInfoView implements IView{


    public Label lb_airline;
    public Label lb_from;
    public Label lb_depart;
    public Label lb_arrival;
    public Label lb_weight;
    public Label lb_to;
    public Label lb_width;
    public Label lb_hieght;
    private VacationInfoController controller;

    @Override
    public void setController(AController controller) {
        this.controller = (VacationInfoController) controller;
        this.controller.initializeView();
    }
}
