package se.mdh.driftstorning.admin.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import se.mdh.driftstorning.common.model.KanalPost;

public interface KanalRepository extends MongoRepository<KanalPost, String> {
  List<KanalPost> findAllByOrderByTextAsc();

}
