package CreatePackage;

import interfaces.IPapaListener;

import java.util.ArrayList;

public class CreateController {

    private CreateModel model;
    private CreateView view;

    private static ArrayList<IPapaListener> lst;

    public CreateController(CreateModel model, CreateView view) {
        this.model = model;
        this.view = view;
        lst = new ArrayList<IPapaListener>();
    }

    public void addPapaListener(IPapaListener papa){
        if (papa != null && !lst.contains(papa))
            lst.add(papa);
    }

}
