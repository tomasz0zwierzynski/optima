package pl.tomzwi.tokenauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomzwi.tokenauth.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
