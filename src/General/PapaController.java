package General;

import Controllers.*;
import Models.*;
import Views.IView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class PapaController {

    private Stage stage;
    private Scene scene;
    public enum Views {HomePage, RegisterWindow, HomePageLoggedIn, LoginWindow,VacationInfo,VacationInfoLoggedIn,CreateVationWindow, MassegesRequests, PurchesVacation};

    private HashMap<Views,String> views;
    private HashMap<Views, AController> controllers;

    public PapaController(Stage primaryStage) {

        this.stage = primaryStage;
        this.views = new HashMap<>();
        this.controllers = new HashMap<>();

        UserModel userModel = new UserModel();
        ModelTicketDB modelTicketDB = new ModelTicketDB();
        VacationModel vacationModel = new VacationModel(userModel,modelTicketDB);
        PurchaseVacationModel purchaseVacationModel = new PurchaseVacationModel();


        this.views.put(Views.HomePage,"/HomePage.fxml");
        this.views.put(Views.LoginWindow,"/Login.fxml");
        this.views.put(Views.RegisterWindow,"/Register.fxml");
        this.views.put(Views.HomePageLoggedIn,"/HomePageLoggedin.fxml");
        this.views.put(Views.CreateVationWindow,"/createVacations2.fxml");
        this.views.put(Views.VacationInfo,"/VacationInfo.fxml");
        this.views.put(Views.VacationInfoLoggedIn,"/VacationInfoLoggedin.fxml");
        this.views.put(Views.MassegesRequests,"/MassagesRequests.fxml");
        this.views.put(Views.PurchesVacation,"/PurchaseVacation.fxml");



        this.controllers.put(Views.HomePage,new HomePageController(this,new HomePageModel(userModel,vacationModel)));
        this.controllers.put(Views.LoginWindow,new LoginController(this,userModel));
        this.controllers.put(Views.RegisterWindow,new RegisterController(this,userModel));
        this.controllers.put(Views.HomePageLoggedIn,new HomePageLoggedInController(this,new HomePageLoggedinModel(userModel,vacationModel)));
        this.controllers.put(Views.CreateVationWindow,new CreateVacationsController(this,vacationModel));
        this.controllers.put(Views.VacationInfo,new VacationInfoController(this,vacationModel));
        this.controllers.put(Views.VacationInfoLoggedIn,new VacationInfoLoggedinController(this,new VacationInfoLoggedinModel(userModel,vacationModel,purchaseVacationModel)));
        this.controllers.put(Views.MassegesRequests,new MassagesRequestsController(this,purchaseVacationModel));
        this.controllers.put(Views.PurchesVacation, new PurchaseVacationController(this,purchaseVacationModel));


    }

    public void SwapScene(Views ViewID) {
        loadView(ViewID);
        stage.setScene(this.scene);
        stage.show();
    }

    private void loadView(Views viewID){
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource(this.views.get(viewID)).openStream());
            IView view = loader.getController();
            controllers.get(viewID).setView(view);
            view.setController(controllers.get(viewID));


            this.scene = new Scene(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
