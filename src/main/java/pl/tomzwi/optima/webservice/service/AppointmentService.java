package pl.tomzwi.optima.webservice.service;

import pl.tomzwi.optima.webservice.persistance.object.AppointmentObject;
import pl.tomzwi.optima.webservice.persistance.object.AppointmentUpdateObject;

import java.util.List;

public interface AppointmentService {
    List<AppointmentObject> getAppointmentByPatient(long patientId);
    AppointmentObject findById(long patientId, long id);
    void deleteAppointment(long patientId, long id);
    AppointmentObject updateAppointment(long patientId, long id, AppointmentUpdateObject update);
    AppointmentObject addAppointmentForPatient(long patientId, AppointmentObject appointment);
}
