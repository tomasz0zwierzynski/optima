package pl.tomzwi.optima.webservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomzwi.optima.webservice.exception.AppointmentNotFoundException;
import pl.tomzwi.optima.webservice.exception.AppointmentPatientMismatchException;
import pl.tomzwi.optima.webservice.exception.PatientNotFoundException;
import pl.tomzwi.optima.webservice.persistance.entity.Appointment;
import pl.tomzwi.optima.webservice.persistance.entity.Patient;
import pl.tomzwi.optima.webservice.persistance.object.AppointmentObject;
import pl.tomzwi.optima.webservice.persistance.object.AppointmentUpdateObject;
import pl.tomzwi.optima.webservice.persistance.repository.AppointmentRepository;
import pl.tomzwi.optima.webservice.persistance.repository.PatientRepository;
import pl.tomzwi.optima.webservice.service.AppointmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<AppointmentObject> getAppointmentByPatient(long patientId) {
        List<Appointment> byPatientId = appointmentRepository.findByPatientId(patientId);
        return byPatientId.stream().map(AppointmentObject::new).collect(Collectors.toList());
    }

    @Override
    public AppointmentObject findById(long patientId, long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Not found"));
        if (!appointment.getPatient().getId().equals(patientId)) throw new AppointmentPatientMismatchException("Not matching");
        return new AppointmentObject(appointment);
    }

    @Override
    public void deleteAppointment(long patientId, long id) {
        Appointment toDelete = appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Not found"));
        if (!toDelete.getPatient().getId().equals(patientId)) throw new AppointmentPatientMismatchException("Not matching");
        appointmentRepository.delete(toDelete);
    }

    @Override
    public AppointmentObject updateAppointment(long patientId, long id, AppointmentUpdateObject update) {
        Appointment toModify = appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Not found"));
        if (!toModify.getPatient().getId().equals(patientId)) throw new AppointmentPatientMismatchException("Not matching");

        if ( update.getAppointmentDate() != null ) toModify.setAppointmentDate( update.getAppointmentDate() );
        if ( update.getDescription() != null ) toModify.setDescription( update.getDescription() );
        if ( update.getNote() != null ) toModify.setNote( update.getNote() );
        if ( update.getPatientId() != null ) {
            Patient patient = patientRepository.findById( update.getPatientId() ).orElseThrow(() -> new PatientNotFoundException("Not found"));
            toModify.setPatient(patient);
        }

        appointmentRepository.save(toModify);
        return new AppointmentObject(toModify);
    }

    @Override
    public AppointmentObject addAppointmentForPatient(long patientId, AppointmentObject appointment) {
        Appointment entity = new Appointment();
        if (!entity.getPatient().getId().equals(patientId)) throw new AppointmentPatientMismatchException("Not matching");

        entity.setAppointmentDate( appointment.getAppointmentDate() );
        entity.setDescription( appointment.getDescription() );
        entity.setNote( appointment.getNote() );
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new AppointmentNotFoundException("Not Found"));
        entity.setPatient(patient);

        appointmentRepository.save(entity);
        return new AppointmentObject(entity);
    }
}
