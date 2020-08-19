package pl.tomzwi.tokenauth.service;

import org.springframework.data.domain.Page;
import pl.tomzwi.tokenauth.entity.Patient;
import pl.tomzwi.tokenauth.model.PageResponse;
import pl.tomzwi.tokenauth.model.PatientObject;
import pl.tomzwi.tokenauth.model.PatientUpdateObject;

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
