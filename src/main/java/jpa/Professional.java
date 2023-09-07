package jpa;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Professionals")
public class Professional extends User {

    @Column(name = "name")
    private String name;

    public Professional(String email, String password, String name) {
        super(email, password);
        this.name = name;
    }

    public Professional() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
