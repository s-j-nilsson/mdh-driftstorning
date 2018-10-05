package se.mdh.driftstorning.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.mdh.driftstorning.common.model.DriftstorningPost;

public interface DriftstorningRepository extends MongoRepository<DriftstorningPost, String> {

}
