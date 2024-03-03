package orgs.Entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter

@Entity
@Table(name="profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Column(name="fullname")
    private String fullName;

    @Column(name="phone_number",unique = true)
    private long phoneNumber;

    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;


    @ManyToMany
    @JoinTable(
            name = "profile_hobby",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    private List<Hobby> hobbyList=new ArrayList<>();

    @OneToOne(mappedBy="profile")
    private User user;


    protected Profile(){

    }

    public Profile(String fullName, long phoneNumber,Address address) {
        super();
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address=address;
    }

}
