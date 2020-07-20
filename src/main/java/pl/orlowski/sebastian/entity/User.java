package pl.orlowski.sebastian.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String login;
    private String password;
    private Timestamp timestamp;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Topic> topic;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Inscription> inscription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Set<Topic> getTopic() {
        return topic;
    }

    public void setTopic(Set<Topic> topic) {
        this.topic = topic;
    }

    public Set<Inscription> getInscription() {
        return inscription;
    }

    public void setInscription(Set<Inscription> inscription) {
        this.inscription = inscription;
    }
}
