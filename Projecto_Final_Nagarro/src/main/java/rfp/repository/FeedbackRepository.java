package rfp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rfp.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}