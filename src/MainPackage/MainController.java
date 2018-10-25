package MainPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class MainController implements ActionListener {

    private MainModel model;
    private MainView view;

    public MainController(MainModel m_model, MainView m_view) {
        this.model = m_model;
        this.view = m_view;
    }


    public void handle(){
        System.out.println("gh");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
