package se.mdh.driftstorning.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.mdh.driftstorning.common.model.KanalPost;

public interface KanalRepository extends MongoRepository<KanalPost, String> {

}
