package pl.tomzwi.optima.webservice.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomzwi.optima.webservice.persistance.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
