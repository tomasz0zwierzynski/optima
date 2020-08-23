package pl.tomzwi.optima.webservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.tomzwi.optima.webservice.persistance.object.AppointmentObject;
import pl.tomzwi.optima.webservice.persistance.object.AppointmentUpdateObject;

import java.util.List;

@RequestMapping("/api")
@Validated
public interface AppointmentController {

    @GetMapping("/patients/{patientId}/appointments")
    ResponseEntity<List<AppointmentObject>> getAllAppointmentsForPatient(@PathVariable long patientId);

    @GetMapping("/patients/{patientId}/appointments/{appointmentId}")
    ResponseEntity<AppointmentObject> getAppointmentForPatient(@PathVariable long patientId, @PathVariable long appointmentId);

    @DeleteMapping("/patients/{patientId}/appointments/{appointmentId}")
    ResponseEntity deleteAppointment(@PathVariable long patientId, @PathVariable long appointmentId);

    @PostMapping("/patients/{patientId}/appointments")
    ResponseEntity<AppointmentObject> addAppointment(@PathVariable long patientId, @RequestBody AppointmentObject appointment);

    @PutMapping("/patients/{patientId}/appointments/{appointmentId}")
    ResponseEntity<AppointmentObject> updateAppointment(@PathVariable long patientId, @PathVariable long appointmentId, @RequestBody AppointmentUpdateObject update);

}
