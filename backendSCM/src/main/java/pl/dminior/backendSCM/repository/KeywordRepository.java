package pl.dminior.backendSCM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dminior.backendSCM.model.Keyword;

import java.util.UUID;

public interface KeywordRepository extends JpaRepository<Keyword, UUID> {
}
