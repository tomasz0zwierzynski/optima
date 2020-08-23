package pl.tomzwi.optima.webservice.persistance.object;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tomzwi.optima.webservice.persistance.entity.Patient;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class PatientObject {
    private long id;
    @NotNull @Max(20) private String firstName;
    @NotNull @Max(80) private String lastName;
    @NotNull @Pattern(regexp = "^[0-9]{6}$") private String personalId;
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
