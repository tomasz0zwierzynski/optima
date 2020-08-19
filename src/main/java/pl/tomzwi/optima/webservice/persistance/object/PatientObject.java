package pl.tomzwi.optima.webservice.persistance.object;

import com.sun.istack.NotNull;
import lombok.Data;
import pl.tomzwi.optima.webservice.persistance.entity.Patient;

@Data
public class PatientObject {
    private long id;
    @NotNull private String firstName;
    @NotNull private String lastName;
    @NotNull private String personalId;
    private String address;
    @NotNull private Patient.Sex sex;

    public PatientObject(Patient entity) {
        this.id = entity.getId();
        this.address = entity.getAddress();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.personalId = entity.getPersonalIdentityNumber();
        this.address = entity.getAddress();
        this.sex = entity.getSex();
    }

}
