package pl.tomzwi.optima.webservice.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.tomzwi.optima.webservice.controller.AppointmentController;
import pl.tomzwi.optima.webservice.persistance.object.AppointmentObject;
import pl.tomzwi.optima.webservice.persistance.object.AppointmentUpdateObject;
import pl.tomzwi.optima.webservice.service.AppointmentService;

import java.util.List;

@RestController
public class AppointmentControllerImpl implements AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Override
    public ResponseEntity<List<AppointmentObject>> getAllAppointmentsForPatient(long patientId) {
        return new ResponseEntity<>(appointmentService.getAppointmentByPatient(patientId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AppointmentObject> getAppointmentForPatient(long patientId, long appointmentId) {
        return new ResponseEntity<>(appointmentService.findById(patientId, appointmentId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteAppointment(long patientId, long appointmentId) {
        appointmentService.deleteAppointment(patientId, appointmentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<AppointmentObject> addAppointment(long patientId, AppointmentObject appointment) {
        return new ResponseEntity<>(appointmentService.addAppointmentForPatient(patientId, appointment), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AppointmentObject> updateAppointment(long patientId, long appointmentId, AppointmentUpdateObject update) {
        return new ResponseEntity<>(appointmentService.updateAppointment(patientId, appointmentId, update), HttpStatus.ACCEPTED);
    }
}
