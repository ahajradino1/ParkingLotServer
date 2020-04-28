package ba.unsa.etf.zavrsni.server.repositories;

import ba.unsa.etf.zavrsni.server.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
