package se.mdh.driftstorning.admin.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import se.mdh.driftstorning.common.model.DriftstorningPost;

public interface DriftstorningRepository extends MongoRepository<DriftstorningPost, String> {
  List<DriftstorningPost> findByMeddelandeSvIsNotNull();
  List<DriftstorningPost> findByMeddelandeEnIsNotNull();

}
