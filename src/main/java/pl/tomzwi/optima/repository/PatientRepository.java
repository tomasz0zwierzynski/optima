package pl.tomzwi.optima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tomzwi.optima.entity.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.firstName LIKE %:text% OR p.lastName LIKE %:text%")
    List<Patient> findByFirstNameLastNameSearch(@Param("text") String text);

    @Query("SELECT p FROM Patient p WHERE p.personalIdentityNumber LIKE %:text%")
    List<Patient> findByPersonalIdSearch(@Param("text") String text);

}
