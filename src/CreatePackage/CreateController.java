package CreatePackage;

import General.AController;
import General.PapaController;
import interfaces.IView;

public class CreateController extends AController {

    private CreateView view;

    public CreateController(PapaController papa) {
        super(papa);
    }

    @Override
    public void setView(IView view) {
        this.view = (CreateView) view;
    }

    @Override
    public void back()
    {

    }

    public void b_create(){
        model.addUser(view.getUserName(),view.getPassword(),view.getFirstName(),view.getLastName(),view.getBirthDate(),view.getCity());
    }
}
