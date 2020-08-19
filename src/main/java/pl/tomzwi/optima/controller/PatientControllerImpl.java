package pl.tomzwi.optima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.tomzwi.optima.model.PageResponse;
import pl.tomzwi.optima.model.PatientObject;
import pl.tomzwi.optima.model.PatientUpdateObject;
import pl.tomzwi.optima.service.PatientService;

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
    public ResponseEntity<List<PatientObject>> searchPatientsByName(@Size(min = 3) String text) {
        return new ResponseEntity<>( patientService.searchPatientByName(text), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PatientObject>> searchPatientsByPersonalId(@Size(min = 3) String text) {
        return new ResponseEntity<>( patientService.searchByPersonalId(text), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PatientObject> getPatient(long patientId) {
        return new ResponseEntity<>( patientService.findById( patientId ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PatientObject> addPatient(PatientObject patient) {
        return new ResponseEntity<>( patientService.addPatient(patient), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PatientObject> updatePatient(Long patientId, PatientUpdateObject patient) {
        return new ResponseEntity<>( patientService.updatePatient(patientId, patient), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deletePatient(Long patientId) {
        patientService.deletePatient( patientId );
        return new ResponseEntity(HttpStatus.OK);
    }
}
