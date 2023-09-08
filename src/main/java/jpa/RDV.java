package jpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "RDV")
@NamedQuery(name = "getRDVFromAProfessional", query = "SELECT r FROM RDV r WHERE r.professional = :pro")
public class RDV {
    private Long id;

    private Date start_time;

    private int duration;

    private String name;

    private Client client;

    private Professional professional;

    private boolean isReserved;

    public RDV(Date start_time, int duration, String name, Professional professional, Client client) {
        this.start_time = start_time;
        this.duration = duration;
        this.name = name;
        this.professional = professional;
        this.client = client;
        this.isReserved = true;
    }

    public RDV(Date start_time, int duration, String name, Professional professional) {
        this.start_time = start_time;
        this.duration = duration;
        this.name = name;
        this.professional = professional;
        this.isReserved = false;
    }

    public RDV(){};


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne()
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne()
    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
