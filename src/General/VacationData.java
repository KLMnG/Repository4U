package General;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class VacationData {

    private int code;

    private String VacationType;

    public enum State {FOR_SELL,FOR_EXCHANGE,INVISIBLE;}

    private HotelData hotel;
    private State state;
    private final SimpleIntegerProperty Days;
    private final SimpleStringProperty Travelers;
    private final SimpleIntegerProperty Price;

    private List<TicketData> ticketData;

    private User Seller;

    public User getSeller() {
        return Seller;
    }

    public void setSeller(User seller) {
        Seller = seller;
    }

    public void setStateToExchange() {
        state = State.FOR_EXCHANGE;
    }

    public void setStateToInVisible() {
        state = State.INVISIBLE;
    }

    public void setStateToSell() {
        state = State.FOR_SELL;
    }

    /**
     * public State getState() {
     * return state;
     * }
     **/

    public VacationData(List<TicketData> ticketData, int price, String vacationType, int code,int timeToStay, User seller, String hotel) {
        this.ticketData = ticketData;
        this.VacationType = vacationType;
        this.Days = new SimpleIntegerProperty(timeToStay);
        this.Travelers = new SimpleStringProperty(ticketData.size() + "");
        this.Price = new SimpleIntegerProperty(price);
        this.code = code;
        this.Seller = seller;
        this.hotel = new HotelData(hotel,"","");
        this.state = State.FOR_SELL;
    }

    public VacationData(List<TicketData> ticketData, int price, String vacationType, int code,int timeToStay, User seller, HotelData hotel) {
        this.ticketData = ticketData;
        this.VacationType = vacationType;
        this.Days = new SimpleIntegerProperty(timeToStay);
        this.Travelers = new SimpleStringProperty(ticketData.size() + "");
        this.Price = new SimpleIntegerProperty(price);
        this.code = code;
        this.Seller = seller;
        this.hotel = hotel;
        this.state = State.FOR_SELL;
    }

    public VacationData(List<TicketData> ticketData, int price, String vacationType, int code,int timeToStay, User seller, HotelData hotel, State state) {
        this.ticketData = ticketData;
        this.VacationType = vacationType;
        this.Days = new SimpleIntegerProperty(timeToStay);
        this.Travelers = new SimpleStringProperty(ticketData.size() + "");
        this.Price = new SimpleIntegerProperty(price);
        this.code = code;
        this.Seller = seller;
        this.hotel = hotel;
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<TicketData> getTicketData() {
        return ticketData;
    }

    public void addToTicketData(TicketData ticketDataToAdd) {
        ticketData.add(ticketDataToAdd);
    }

    public int getDays() {
        return Days.get();
    }

    public void setDays(int days) {
        this.Days.set(days);
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

    public String getFrom() {
        return this.ticketData.get(0).getFrom();
    }

    public String getTo() {
        return this.ticketData.get(0).getTo();
    }

    public String getDepart() {
        return this.ticketData.get(0).getDepart();
    }

    public String getState() {
        return state.toString();
    }

    public HotelData getHotel() {
        return this.hotel;
    }

}
