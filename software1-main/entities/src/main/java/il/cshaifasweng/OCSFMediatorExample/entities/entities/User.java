package il.cshaifasweng.OCSFMediatorExample.entities.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    private int userId;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "isLoggedIn")
    private boolean isLoggedIn = false;

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public User() {

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User(int user_id, String password) {
        this.userId = user_id;
        this.password = password;
    }

    public User(int user_id, String password, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = user_id;
        this.password = password;
    }

    public int getUser_id() {
        return userId;
    }

    public String getPassword() {
        return password;
    }


    public void setUser_id(int user_id) {
        this.userId = user_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "" + firstName + " " + lastName; // Display the course name in the ComboBox
    }

}
