package rfp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rfp.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}