package jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Clients")
public class Client extends User{

    private List<RDV> appointments = new ArrayList<RDV>();


    public Client(String email, String password, String name) {
        super(email, password, name);
    }

    public Client(){};

    @OneToMany(mappedBy = "client")
    public List<RDV> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<RDV> appointments) {
        this.appointments = appointments;
    }
}
