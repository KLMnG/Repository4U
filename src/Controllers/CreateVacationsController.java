package Controllers;

import General.PapaController;
import Models.VacationModel;
import Views.CreateVacationsView;
import Views.IView;

public class CreateVacationsController extends AController{

    private CreateVacationsView view;
    private VacationModel model;
    public CreateVacationsController(PapaController papa, VacationModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (CreateVacationsView) view;
    }
}
