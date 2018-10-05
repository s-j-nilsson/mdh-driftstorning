package se.mdh.driftstorning.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.mdh.driftstorning.common.model.AnledningPost;

public interface AnledningRepository extends MongoRepository<AnledningPost, String> {

}
