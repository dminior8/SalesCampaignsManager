package pl.dminior.backendSCM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dminior.backendSCM.model.Keyword;

import java.util.List;
import java.util.UUID;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, UUID> {
    @Query(value = "SELECT DISTINCT * FROM keywords",
            nativeQuery = true)
    List<Keyword> getAll();
}
