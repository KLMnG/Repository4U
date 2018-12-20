package General;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.SimpleStringProperty;

public class TicketData {


    private SimpleStringProperty code;
    private SimpleStringProperty From;
    private SimpleStringProperty To;
    private SimpleStringProperty Depart;
    private SimpleStringProperty Airline;
    private int weight;
    private int height;
    private int width;
    private String TicketType;
    private boolean includes_flight_back;
    private int vacation;



    public TicketData(String code, String from, String to, String depart, String airline, int weight, int height, int width, String ticketType, int includes_flight_back, int vacation) {
        this.code = new SimpleStringProperty(code);
        this.From = new SimpleStringProperty(from);
        this.To = new SimpleStringProperty(to);
        this.Depart = new SimpleStringProperty(depart);
        this.Airline = new SimpleStringProperty(airline);
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.TicketType = ticketType;
        this.includes_flight_back = (includes_flight_back != 0);
        this.vacation = vacation;

    }


    public boolean getIncludes_flight_back() {
        return includes_flight_back;
    }

    public void setIncludes_flight_back(boolean includes_flight_back) {
        this.includes_flight_back = includes_flight_back;
    }

    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getFrom() {
        return From.get();
    }

    public void setFrom(String from) {
        From.set(from);
    }

    public String getTo() {
        return To.get();
    }

    public void setTo(String to) {
        To.set(to);
    }

    public String getDepart() {
        return Depart.get();
    }

    public void setDepart(String depart) {
        Depart.set(depart);
    }

    public String getAirline() {
        return Airline.get();
    }

    public void setAirline(String airline) {
        Airline.set(airline);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTicketType() {
        return TicketType;
    }

    public void setTicketType(String ticketType) {
        TicketType = ticketType;
    }

    public int getVacation() {
        return vacation;
    }

    public void setVacation(int vacation) {
        this.vacation = vacation;
    }



}
