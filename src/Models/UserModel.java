package Models;

import General.DBConnection;
import General.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

