package pl.tomzwi.optima.model;

import lombok.Data;
import pl.tomzwi.optima.entity.Patient;

@Data
public class PatientUpdateObject {
    private String firstName;
    private String lastName;
    private String personalId;
    private String address;
    private Patient.Sex sex;
}
