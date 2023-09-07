package jpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "RDV")
public class RDV {
    private Long id;

    private Date start_time;

    private int duration;

    private String name;

    public RDV(Date start_time, int duration, String name, Professional professional) {
        this.start_time = start_time;
        this.duration = duration;
        this.name = name;
        this.professional = professional;
    }

    public RDV(){};

    private Client client;

    private Professional professional;

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
}
