package app.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.main.entities.SoftwareEntity;

public interface SoftwareRepository extends JpaRepository<SoftwareEntity, Long>{

}
