package pl.tomzwi.optima.webservice.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import pl.tomzwi.optima.webservice.exception.PatientNotFoundException;
import pl.tomzwi.optima.webservice.model.PageResponse;
import pl.tomzwi.optima.webservice.persistance.object.PatientObject;
import pl.tomzwi.optima.webservice.service.PatientService;
import pl.tomzwi.optima.webservice.persistance.object.PatientUpdateObject;
import pl.tomzwi.optima.webservice.controller.PatientController;

import javax.validation.constraints.Size;
import java.util.List;

@RestController
public class PatientControllerImpl implements PatientController {

    @Autowired
    private PatientService patientService;

    @Override
    public ResponseEntity<PageResponse<PatientObject>> getAllPatients(int perPage, int page) {
        return new ResponseEntity<>( patientService.getPatientPage(perPage, page), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<PatientObject>> searchPatientsByName(String text) {
        return new ResponseEntity<>( patientService.searchPatientByName(text), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PatientObject>> searchPatientsByPersonalId(String text) {
        return new ResponseEntity<>( patientService.searchByPersonalId(text), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PatientObject> getPatient(long patientId) {
        return new ResponseEntity<>( patientService.findById( patientId ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PatientObject> addPatient(PatientObject patient) {
        return new ResponseEntity<>( patientService.addPatient(patient), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PatientObject> updatePatient(Long patientId, PatientUpdateObject patient) {
        return new ResponseEntity<>( patientService.updatePatient(patientId, patient), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity deletePatient(Long patientId) {
        patientService.deletePatient( patientId );
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity patientNotFoundHandler() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
