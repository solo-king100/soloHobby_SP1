package orgs.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter

@Entity
@Table(name="hobby")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private  int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy ="hobbyList" )
    private List<Profile> profiles = new ArrayList<>();

    protected Hobby(){

    }

    public Hobby( String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }


}
