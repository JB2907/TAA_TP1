package jpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Professionals")
public class Professional extends User {

    private List<RDV> appointments = new ArrayList<RDV>();

    public Professional(String email, String password, String name) {
        super(email, password, name);
    }

    public Professional() {}

    @OneToMany(mappedBy = "professional")
    public List<RDV> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<RDV> appointments) {
        this.appointments = appointments;
    }

}
