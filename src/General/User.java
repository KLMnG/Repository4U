package General;

public class User {

    private String Username;
    private String Password;
    private String FirstName;
    private String LastName;
    private String BirthDate;
    private String City;

    public User(String Username, String Password, String Firstname, String Lastname, String Birthdate, String city){
        this.Username = Username;
        this.Password = Password;
        this.FirstName = Firstname;
        this.LastName = Lastname;
        this.BirthDate = Birthdate;
        this.City = city;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getBirthDate() {
        return this.BirthDate;
    }

    public void setBirthDate(String birthDate) {
        this.BirthDate = birthDate;
    }

    public String getCity() {
        return this.City;
    }

    public void setCity(String city) {
        this.City = city;
    }




}
