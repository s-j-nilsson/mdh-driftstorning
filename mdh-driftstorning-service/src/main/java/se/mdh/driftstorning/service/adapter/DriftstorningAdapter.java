package se.mdh.driftstorning.service.adapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    driftstorning.setMeddelandeSv(driftstorningPost.getAnledningPost().getMeddelandeSv());
    driftstorning.setStart(LocalDateTime.of(driftstorningPost.getStartDatum() != null ? driftstorningPost.getStartDatum() : LocalDate.now(),
                                            driftstorningPost.getStartTid()));
    driftstorning.setSlut(LocalDateTime.of(driftstorningPost.getSlutDatum() != null ? driftstorningPost.getSlutDatum() : LocalDate.now(), driftstorningPost.getSlutTid()));
    return driftstorning;
  }
}
