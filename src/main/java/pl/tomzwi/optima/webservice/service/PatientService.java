package pl.tomzwi.optima.webservice.service;

import pl.tomzwi.optima.webservice.model.PageResponse;
import pl.tomzwi.optima.webservice.persistance.object.PatientObject;
import pl.tomzwi.optima.webservice.persistance.object.PatientUpdateObject;

import java.util.List;

public interface PatientService {
    PageResponse<PatientObject> getPatientPage(int perPage, int page);
    List<PatientObject> searchPatientByName(String text);
    List<PatientObject> searchByPersonalId(String personalId);
    PatientObject findById(long id);
    void deletePatient(long id);
    PatientObject updatePatient(long id, PatientUpdateObject update);
    PatientObject addPatient(PatientObject patient);
}
