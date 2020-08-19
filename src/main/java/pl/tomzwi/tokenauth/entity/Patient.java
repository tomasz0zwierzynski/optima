package pl.tomzwi.tokenauth.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "patients" )
@Data
public class Patient {

    public enum Sex {
        MALE,
        FEMALE,
        UNKNOWN
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "firstname", nullable = false)
    private String firstName;

    @Column( name = "lastname", nullable = false )
    private String lastName;

    @Column(name = "personalidentitynumber", nullable = false )
    private String personalIdentityNumber;

    @Column( nullable = false )
    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String address;

    @OneToMany( mappedBy = "patient" )
    @ToString.Exclude
    private List<Appointment> appointments;

}
