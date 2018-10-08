package se.mdh.driftstorning.admin.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import se.mdh.driftstorning.common.model.AnledningPost;
import se.mdh.driftstorning.common.model.KanalPost;

public interface AnledningRepository extends MongoRepository<AnledningPost, String> {
  List<AnledningPost> findAllByOrderByTextAsc();

}
