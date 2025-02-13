package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "managers")
public class Manager extends User implements Serializable{

    public Manager(int id,String password,String firstName, String lastName) {
        this.setUser_id(id);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }


    public Manager() {

    }
}
