package app.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.main.entities.BugEntity;

public interface BugRepository extends JpaRepository<BugEntity, Long>{

}
