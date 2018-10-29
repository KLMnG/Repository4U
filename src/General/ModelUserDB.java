package General;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelUserDB {


    private DBConnection con;

    public ModelUserDB() {
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
            e.printStackTrace();
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
            System.out.println(e.getMessage());
        }
    }
    public void update(String UserName, String Password, String FirstName, String LastName, String Address, String BirthDate) {

        String sql = "UPDATE Users SET Password = ? , "
                + "FirstName = ? , LastName = ? , Address = ?, BirthDate = ? "
                + "WHERE Username = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param

            pstmt.setString(1, Password);
            pstmt.setString(2, FirstName);
            pstmt.setString(3, LastName);
            pstmt.setString(4, Address);
            pstmt.setString(5, BirthDate);
            pstmt.setString(6, UserName);

            // update

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<String> read(String UserName){
        List arrl=null;
        String sql = "SELECT Username,Password,FirstName,LastName,Address,BirthDate "
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
                arrl.add(rs.getString("Address"));
                arrl.add(rs.getString("BirthDate"));


            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arrl;
    }

    public List<String> read(String UserName,String Password){
        List arrl=null;
        String sql = "SELECT Username,Password,FirstName,LastName,Address,BirthDate "
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
                arrl.add(rs.getString("Address"));
                arrl.add(rs.getString("BirthDate"));


            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arrl;
    }




}

