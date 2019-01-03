package Models;

import General.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserModel {


    private DBConnection con;
    private static User user;

    public UserModel() {
        con=new DBConnection();
    }

    public void addUser(String UserName, String Password, String BirthDate, String FirstName, String LastName, String City) {
        String sql = "INSERT INTO Users(Username,Password,BirthDate,FirstName,LastName,City) VALUES(?,?,?,?,?,?)";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, UserName);
            pstmt.setString(2, Password);
            pstmt.setString(3, BirthDate);
            pstmt.setString(4, FirstName);
            pstmt.setString(5, LastName);
            pstmt.setString(6, City);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
    public void delete(String UserName, String Password) {
        String sql = "DELETE FROM Users WHERE Username = ? AND  Password=?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, UserName);
            pstmt.setString(2, Password);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
    public void update(String UserName, String Password, String FirstName, String LastName, String City, String BirthDate) {
        String sql = "UPDATE Users SET Password = ? , "
                + "FirstName = ? , LastName = ? , City = ?, BirthDate = ? "
                + "WHERE Username = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param

            pstmt.setString(1, Password);
            pstmt.setString(2, FirstName);
            pstmt.setString(3, LastName);
            pstmt.setString(4, City);
            pstmt.setString(5, BirthDate);
            pstmt.setString(6, UserName);

            // update

            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
    public List<String> read(String UserName){
        List arrl=null;
        String sql = "SELECT Username,Password,FirstName,LastName,City,BirthDate "
                + "FROM Users WHERE Username = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1,UserName);
            ResultSet rs  = pstmt.executeQuery();

            arrl = new  ArrayList();

            while (rs.next()) {

                arrl.add(rs.getString("Username"));
                arrl.add(rs.getString("Password"));
                arrl.add(rs.getString("FirstName"));
                arrl.add(rs.getString("LastName"));
                arrl.add(rs.getString("City"));
                arrl.add(rs.getString("BirthDate"));


            }
        } catch (SQLException e) {
        }
        return arrl;
    }
    public List<String> read(String UserName,String Password){
        List arrl=null;
        String sql = "SELECT Username,Password,FirstName,LastName,City,BirthDate "
                + "FROM Users WHERE Username = ? and Password = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1,UserName);
            pstmt.setString(2,Password);
            ResultSet rs  = pstmt.executeQuery();

            arrl = new  ArrayList();

            while (rs.next()) {

                arrl.add(rs.getString("Username"));
                arrl.add(rs.getString("Password"));
                arrl.add(rs.getString("FirstName"));
                arrl.add(rs.getString("LastName"));
                arrl.add(rs.getString("City"));
                arrl.add(rs.getString("BirthDate"));
            }
        } catch (SQLException e) {
        }
        return arrl;
    }

    public List<VacationData> getMyVacation(String user, VacationData.State state){
        Map<Integer, VacationData> vacations = new HashMap<>();
        List<VacationData> allVacation= new ArrayList<>();;
        VacationData vacationD;
        String sql = "SELECT Tickets.code as ticketCode, flight_company ,departure_date, includes_flight_back, departure_from, destination, ticket_type, weight, height, width, \n" +
                "Users.Username as Username, Users.Password as Password, Users.BirthDate as BirthDate,Users.FirstName as FirstName, Users.LastName as LastName, Users.City as City,\n" +
                "Vacations.code as VacationCode, Vacations.time_to_stay as time_to_stay, Vacations.vacation_type as vacation_type, Vacations.hotel as hotel, Vacations.owner as owner, Vacations.price as price, Vacations.state as state, \n" +
                "Hotels.code as hotelName ,Hotels.address as address, Hotels.rate as rate\n" +
                "FROM Tickets\n" +
                "left join Vacations on Vacations.code = Tickets.vacation\n" +
                "left join Hotels on Hotels.code = Vacations.hotel\n" +
                "left join Users on Vacations.owner = Users.Username\n" +
                "WHERE Vacations.owner = ? AND Vacation.state = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,user);
            pstmt.setString(2,state.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User seller = new User(rs.getString("Username"), "Password", "FirstName", "LastName", "City", "BirthDate");
                String vacationType = rs.getString("vacation_type");
                int price = rs.getInt("price");

                TicketData ticketData = new TicketData(rs.getString("ticketCode"), rs.getString("departure_from"), rs.getString("destination"),
                        rs.getString("departure_date"), rs.getString("flight_company"), rs.getInt("weight"),
                        rs.getInt("height"),rs.getInt("width"), rs.getString("ticket_type"),
                        rs.getInt("includes_flight_back"),rs.getInt("VacationCode"));

                if (!vacations.containsKey(rs.getInt("VacationCode"))) {
                    List<TicketData> lst = new ArrayList<>();
                    lst.add(ticketData);
                    HotelData hotelData = new HotelData(rs.getString("hotelName"),rs.getString("address"),rs.getString("rate"));
                    vacationD = new VacationData(lst,price, vacationType, rs.getInt("VacationCode"), rs.getInt("time_to_stay"), seller,hotelData);
                    allVacation.add(vacationD);
                    vacations.put(rs.getInt("VacationCode"), vacationD);
                } else vacations.get(rs.getInt("VacationCode")).addToTicketData(ticketData);

            }


        } catch (SQLException e) {
        }
        return allVacation;
    }

    public boolean isUserExists(String Username){
        List result = read(Username);
        return (result != null && result.size() > 0);
    }

    public boolean isUserExists(String Username,String Password){
        List result = read(Username,Password);
        return (result != null && result.size() > 0);
    }

    public void Signin(String username,String password){

        List<String> userData= read(username,password);
        if (userData != null)
            this.user = new User(userData.get(0),userData.get(1),userData.get(2),userData.get(3),userData.get(4),userData.get(5));

    }
    public void Signout(String username,String password){
        this.user = null;
    }

    public static boolean isLoggedIn(){
        return !(user == null);
    }

    public static String getUsername() {
        return user.getUsername();
    }

    public static void setUsername(String username) {
        user.setUsername(username);
    }

    public static String getPassword() {
        return user.getPassword();
    }

    public static void setPassword(String password) {
        user.setPassword(password);
    }

    public static String getFirstName() {
        return user.getFirstName();
    }

    public static void setFirstName(String firstName) {
        user.setFirstName(firstName);
    }

    public static String getLastName() {
        return user.getLastName();
    }

    public static void setLastName(String lastName) {
        user.setLastName(lastName);
    }

    public static String getBirthDate() {
        return user.getBirthDate();
    }

    public static void setBirthDate(String birthDate) {
        user.setBirthDate(birthDate);
    }

    public static String getCity() {
        return user.getCity();
    }

    public static void setCity(String city) {
        user.setCity(city);
    }
}

