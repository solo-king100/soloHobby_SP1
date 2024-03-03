package orgs.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name="address")
public class Address {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id",nullable = false)
    private int id;

    @Column(name="city_name")
    private String cityName;

    @Column(name="post_code")
    private int postCode;

    protected  Address(){

    }

    public Address(String cityName, int postCode) {
        super();
        this.cityName = cityName;
        this.postCode = postCode;
    }
}
