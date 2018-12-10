package General;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class VacationData {


    private final SimpleStringProperty From;
    private final SimpleStringProperty To;
    private final SimpleIntegerProperty Days;
    private final SimpleStringProperty Depart;
    private final SimpleStringProperty Travelers;
    private final SimpleIntegerProperty Price;

    private String VacationType;
    private List<TicketData> ticketData;
    private NightStayData nightStayData;

    public VacationData(List<TicketData> ticketData,NightStayData nightStayData,int price,String vacationType) {
        this.ticketData = ticketData;
        this.nightStayData = nightStayData;
        this.VacationType = vacationType;

        this.From = new SimpleStringProperty(ticketData.get(0).getFrom());
        this.To = new SimpleStringProperty(ticketData.get(0).getTo());
        this.Days = new SimpleIntegerProperty(nightStayData.getTimeToStay());
        this.Depart = new SimpleStringProperty(ticketData.get(0).getDepart());
        this.Travelers = new SimpleStringProperty(ticketData.size() + "");
        this.Price = new SimpleIntegerProperty(price);
    }

    public String getFrom() {
        return From.get();
    }

    public void setFrom(String from) {
        this.From.set(from);
    }

    public int getDays() {
        return Days.get();
    }

    public void setDays(int days) {
        this.Days.set(days);
    }

    public String getDepart() {
        return Depart.get();
    }

    public void setDepart(String depart) {
        this.Depart.set(depart);
    }

    public String getTo() {
        return To.get();
    }

    public void setTo(String to) {
        this.To.set(to);
    }

    public String getTravelers() {
        return Travelers.get();
    }

    public void setTravelers(String travelers) {
        this.Travelers.set(travelers);
    }

    public int getPrice() {
        return Price.get();
    }

    public void setPrice(int price) {
        this.Price.set(price);
    }

    public String getVacationType() {
        return VacationType;
    }

    public void setVacationType(String vacationType) {
        VacationType = vacationType;
    }
}