package pl.tomzwi.optima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomzwi.optima.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
