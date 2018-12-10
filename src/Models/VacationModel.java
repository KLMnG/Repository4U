package Models;

public class VacationModel {

    private UserModel userModel;
    private ModelTicketDB modelTicketDB;

    public VacationModel(UserModel userModel, ModelTicketDB modelTicketDB) {
        this.userModel = userModel;
        this.modelTicketDB = modelTicketDB;
    }
}
