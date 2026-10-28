package EntityRelationship;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public UserProfile() {}

    public UserProfile(String address, User user) {
        this.address = address;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }
}