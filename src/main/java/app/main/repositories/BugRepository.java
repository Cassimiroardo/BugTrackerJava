package app.main.repositories;

import app.main.enums.Severity;
import org.springframework.data.jpa.repository.JpaRepository;

import app.main.entities.BugEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BugRepository extends JpaRepository<BugEntity, Long>{

    @Query(value = "SELECT id,\n" +
            "    created_at,\n" +
            "    deadline,\n" +
            "    'description',\n" +
            "    image_url,\n" +
            "    severity,\n" +
            "    software_id\n" +
            "FROM bug_entity\n" +
            "WHERE software_id = ?1\n" +
            "AND severity = ?2",
    nativeQuery = true)
    List<BugEntity> findByIdSoftwareWhereSeverity(Long idSoftware, int severity);

    List<BugEntity> findAllById(Long bugId);

    @Query(value = "SELECT id,\n" +
            "    created_at,\n" +
            "    deadline,\n" +
            "    'description',\n" +
            "    image_url,\n" +
            "    severity,\n" +
            "    software_id\n" +
            "FROM bug_entity\n" +
            "WHERE software_id = ?1",
    nativeQuery = true)
    List<BugEntity> findBySoftwareId(Long idSoftware);

}
