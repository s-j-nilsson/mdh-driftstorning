package se.mdh.driftstorning.service.service;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import se.mdh.driftstorning.service.model.Driftstorningar;

@Service
public class DriftstorningService {
  private static final Log log = LogFactory.getLog(DriftstorningService.class);

  public DriftstorningService() {
  }

  public Driftstorningar getPagaendeDriftstorningar(List<String> kanaler, int marginalMinuter) {
    return new Driftstorningar();
  }
}
