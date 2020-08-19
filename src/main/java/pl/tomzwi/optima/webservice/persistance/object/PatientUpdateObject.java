package pl.tomzwi.optima.webservice.persistance.object;

import lombok.Data;
import pl.tomzwi.optima.webservice.persistance.entity.Patient;

@Data
public class PatientUpdateObject {
    private String firstName;
    private String lastName;
    private String personalId;
    private String address;
    private Patient.Sex sex;
}
