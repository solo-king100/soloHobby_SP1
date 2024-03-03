package orgs.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name="\"User\"")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Column(name="username")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String eMail;


    @OneToOne
    @JoinColumn(name="profile_id",referencedColumnName = "id")
    private Profile profile;

    protected User(){

    }


    public User(String userName, String password, String eMail) {
        super();
        this.userName = userName;
        this.password = password;
        this.eMail = eMail;
    }

}
