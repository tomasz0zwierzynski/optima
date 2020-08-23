package pl.tomzwi.optima.webservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.tomzwi.optima.webservice.model.PageResponse;
import pl.tomzwi.optima.webservice.persistance.object.PatientObject;
import pl.tomzwi.optima.webservice.persistance.object.PatientUpdateObject;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RequestMapping("/api")
@Validated
public interface PatientController {

    @GetMapping("/patients")
    ResponseEntity<PageResponse<PatientObject>> getAllPatients(@RequestParam int perPage, @RequestParam int page);

    @GetMapping("/patients/searchname")
    ResponseEntity<List<PatientObject>> searchPatientsByName(@RequestParam @Size(min = 3) String text);

    @GetMapping("/patients/searchid")
    ResponseEntity<List<PatientObject>> searchPatientsByPersonalId(@RequestParam @Size(min = 3) String text);

    @GetMapping("/patients/{patientId}")
    ResponseEntity<PatientObject> getPatient(@PathVariable long patientId);

    @PostMapping("/patients")
    ResponseEntity<PatientObject> addPatient(@RequestBody @Valid PatientObject patient);

    @PutMapping("/patients/{patientId}")
    ResponseEntity<PatientObject> updatePatient(@PathVariable Long patientId, @RequestBody PatientUpdateObject patient);

    @DeleteMapping("/patients/{patientId}")
    ResponseEntity deletePatient(@PathVariable Long patientId);


}
