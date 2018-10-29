package MainPackage;

import General.AController;
import General.PapaController;
import interfaces.IView;

public class MainController extends AController {

    private MainView view;

    public MainController(PapaController papa) {
        super(papa);
    }

    @Override
    public void setView(IView view) {
        this.view = (MainView) view;
    }

    public void openCreateWindow() {
        SwapScene(PapaController.Views.CreateWindow);
    }

    public void openReadWindow() { SwapScene(PapaController.Views.ReadWindow); }

    public void openUpdateWindow() { SwapScene(PapaController.Views.UpdateWindow); }

    public void openDeleteWindow() { SwapScene(PapaController.Views.DeleteWIndow); }
}
