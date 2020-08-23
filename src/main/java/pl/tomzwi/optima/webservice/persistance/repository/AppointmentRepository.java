package pl.tomzwi.optima.webservice.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tomzwi.optima.webservice.persistance.entity.Appointment;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId")
    List<Appointment> findByPatientId(@Param("patientId") long patientId);
}
