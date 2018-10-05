package se.mdh.driftstorning.service.adapter;

import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import se.mdh.driftstorning.common.model.DriftstorningPost;
import se.mdh.driftstorning.service.model.Driftstorning;

@Component
public class DriftstorningAdapter {
  public Driftstorning convertDriftstorningPost(@NotNull DriftstorningPost driftstorningPost) {
    Driftstorning driftstorning = new Driftstorning();
    driftstorning.setKanal(driftstorningPost.getKanalPost().getNamn());
    driftstorning.setNiva(Driftstorning.Niva.ERROR);
    driftstorning.setOrsakskod(driftstorningPost.getAnledningPost().getNamn());
    driftstorning.setMeddelandeSv(driftstorningPost.getAnledningPost().getMeddelande());
    return driftstorning;
  }
}
