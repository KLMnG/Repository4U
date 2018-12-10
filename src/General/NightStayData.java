package General;

public class NightStayData {
    private int code;
    private int TimeToStay;
    private HotelData hotelData;

    public NightStayData(int code, int timeToStay, HotelData hotelData) {
        this.code = code;
        this.TimeToStay = timeToStay;
        this.hotelData = hotelData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTimeToStay() {
        return TimeToStay;
    }

    public void setTimeToStay(int timeToStay) {
        TimeToStay = timeToStay;
    }

    public HotelData getHotelData() {
        return hotelData;
    }

    public void setHotelData(HotelData hotelData) {
        this.hotelData = hotelData;
    }
}
