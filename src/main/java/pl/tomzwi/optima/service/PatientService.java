package pl.tomzwi.optima.service;

import pl.tomzwi.optima.model.PageResponse;
import pl.tomzwi.optima.model.PatientObject;
import pl.tomzwi.optima.model.PatientUpdateObject;

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
