package pl.tomzwi.tokenauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.tomzwi.tokenauth.entity.Patient;
import pl.tomzwi.tokenauth.exception.PatientNotFoundException;
import pl.tomzwi.tokenauth.model.PageResponse;
import pl.tomzwi.tokenauth.model.PatientObject;
import pl.tomzwi.tokenauth.model.PatientUpdateObject;
import pl.tomzwi.tokenauth.repository.PatientRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PatientServiceImpl implements PatientService  {

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public PageResponse<PatientObject> getPatientPage(int perPage, int page) {
        Pageable pageable = PageRequest.of(page, perPage, Sort.by(Sort.Order.asc("id")));
        Page<Patient> patientPage = patientRepository.findAll(pageable);

        return new PageResponse<>(
                patientPage,
                patientPage.getContent().stream().map(PatientObject::new).collect(Collectors.toList())
        );
    }

    @Override
    public List<PatientObject> searchPatientByName(String text) {
        List<Patient> byFirstNameLastNameSearch = patientRepository.findByFirstNameLastNameSearch(text);

        return byFirstNameLastNameSearch.stream().map(PatientObject::new).collect(Collectors.toList());
    }

    @Override
    public List<PatientObject> searchByPersonalId(String personalId) {
        List<Patient> byPersonalIdSearch = patientRepository.findByPersonalIdSearch(personalId);

        return byPersonalIdSearch.stream().map(PatientObject::new).collect(Collectors.toList());
    }

    @Override
    public PatientObject findById(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Not found"));

        return new PatientObject(patient);
    }

    @Override
    public void deletePatient(long id) {
        Patient toDelete = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Not found"));
        patientRepository.delete(toDelete);
    }

    @Override
    public PatientObject updatePatient(long id, PatientUpdateObject update) {
        Patient toModify = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Not found"));

        if ( update.getFirstName() != null ) toModify.setFirstName( update.getFirstName() );
        if ( update.getLastName() != null ) toModify.setLastName( update.getLastName() );
        if ( update.getAddress() != null ) toModify.setAddress( update.getAddress() );
        if ( update.getPersonalId() != null ) toModify.setPersonalIdentityNumber( update.getPersonalId() );
        if ( update.getSex() != null ) toModify.setSex( update.getSex() );

        patientRepository.save( toModify );
        return new PatientObject( toModify );
    }

    @Override
    public PatientObject addPatient(PatientObject patient) {
        Patient entity = new Patient();

        entity.setLastName( patient.getLastName() );
        entity.setFirstName( patient.getFirstName() );
        entity.setSex( patient.getSex() );
        entity.setPersonalIdentityNumber( patient.getPersonalId() );
        entity.setAddress( patient.getAddress() );

        patientRepository.save( entity );

        return new PatientObject(entity);
    }
}
