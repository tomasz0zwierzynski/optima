package pl.tomzwi.optima.webservice.persistance.object;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.tomzwi.optima.webservice.persistance.entity.Appointment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AppointmentObject {

    private long id;
    @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime appointmentDate;
    @NotNull @Size(max = 4000) private String description;
    private String note;
    @NotNull private long patientId;

    public AppointmentObject(Appointment entity) {
        id = entity.getId();
        appointmentDate = entity.getAppointmentDate();
        description = entity.getDescription();
        note = entity.getNote();
        patientId = entity.getPatient().getId();
    }

}
