package jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clients")
public class Client extends User{
    private String name;

    public Client(String email, String password, String name) {
        super(email, password);
        this.name = name;
    }

    public Client(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
