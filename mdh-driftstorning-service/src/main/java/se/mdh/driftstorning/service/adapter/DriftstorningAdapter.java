package se.mdh.driftstorning.service.adapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import se.mdh.driftstorning.common.model.DriftstorningPost;
import se.mdh.driftstorning.service.model.Driftstorning;

@Component
public class DriftstorningAdapter {
  public Driftstorning convertDriftstorningPost(@NotNull DriftstorningPost driftstorningPost) {
    Driftstorning driftstorning = new Driftstorning();
    driftstorning.setDriftstorningId(driftstorningPost.getId());
    driftstorning.setKanal(driftstorningPost.getKanalPost().getNamn());
    driftstorning.setNiva(Driftstorning.Niva.valueOf(driftstorningPost.getNiva().name()));
    driftstorning.setOrsakskod(driftstorningPost.getAnledningPost().getNamn());
    driftstorning.setMeddelandeSv(StringUtils.isEmpty(driftstorningPost.getMeddelandeSv()) ? driftstorningPost.getAnledningPost().getMeddelandeSv() : driftstorningPost.getMeddelandeSv());
    driftstorning.setMeddelandeEn(StringUtils.isEmpty(driftstorningPost.getMeddelandeEn()) ? driftstorningPost.getAnledningPost().getMeddelandeEn() : driftstorningPost.getMeddelandeEn());
    driftstorning.setStart(LocalDateTime.of(driftstorningPost.getStartDatum() != null ? driftstorningPost.getStartDatum() : LocalDate.now(),
                                            driftstorningPost.getStartTid()));
    driftstorning.setSlut(LocalDateTime.of(driftstorningPost.getSlutDatum() != null ? driftstorningPost.getSlutDatum() : LocalDate.now(), driftstorningPost.getSlutTid()));
    return driftstorning;
  }
}
