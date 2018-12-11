package General;

public class NightStayData {
    private int code;
    private HotelData hotelData;

    public NightStayData(int code, HotelData hotelData) {
        this.code = code;
        this.hotelData = hotelData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HotelData getHotelData() {
        return hotelData;
    }

    public void setHotelData(HotelData hotelData) {
        this.hotelData = hotelData;
    }
}
