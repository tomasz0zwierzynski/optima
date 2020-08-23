package pl.tomzwi.optima.webservice.persistance.object;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class AppointmentUpdateObject {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime appointmentDate;
    private String description;
    private String note;
    private Long patientId;
}
