package pl.tomzwi.optima.webservice.persistance.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "appointments" )
@Data
public class Appointment {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "appointmentdate", nullable = false)
    private LocalDateTime appointmentDate;

    @Column( nullable = false )
    private String description;

    @Column( nullable = false )
    private String note;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "patientid" )
    @ToString.Exclude
    private Patient patient;

}
