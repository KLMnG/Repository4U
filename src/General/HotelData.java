package General;

public class HotelData {

    private String code;
    private String Address;
    private String rate;

    public HotelData(String code, String address, String rate) {
        this.code = code;
        this.Address = address;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
