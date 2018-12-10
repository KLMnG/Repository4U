package General;

import javafx.beans.property.SimpleStringProperty;

public class TicketData {

    private String From;
    private String To;
    private String Depart;
    private String Airline;
    private LuggageData luggageData;
    private String TicketType;
    private User Seller;

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getDepart() {
        return Depart;
    }

    public void setDepart(String depart) {
        Depart = depart;
    }

    public String getAirline() {
        return Airline;
    }

    public void setAirline(String airline) {
        Airline = airline;
    }

    public LuggageData getLuggageData() {
        return luggageData;
    }

    public void setLuggageData(LuggageData luggageData) {
        this.luggageData = luggageData;
    }

    public String getTicketType() {
        return TicketType;
    }

    public void setTicketType(String ticketType) {
        TicketType = ticketType;
    }

    public User getSeller() {
        return Seller;
    }

    public void setSeller(User seller) {
        Seller = seller;
    }

    public TicketData(String from, String to, String depart, String airline, LuggageData luggageData, String ticketType, User seller) {
        this.From = from;
        this.To = to;
        this.Depart = depart;
        this.Airline = airline;
        this.luggageData = luggageData;
        this.TicketType = ticketType;
        this.Seller = seller;
    }
}