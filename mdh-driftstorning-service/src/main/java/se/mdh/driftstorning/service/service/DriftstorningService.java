package se.mdh.driftstorning.service.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import se.mdh.driftstorning.common.model.DriftstorningPost;
import se.mdh.driftstorning.service.adapter.DriftstorningarAdapter;
import se.mdh.driftstorning.service.model.Driftstorning;
import se.mdh.driftstorning.service.model.Driftstorningar;
import se.mdh.driftstorning.service.repository.DriftstorningRepository;

@Service
public class DriftstorningService {
  private static final Log log = LogFactory.getLog(DriftstorningService.class);

  private final DriftstorningRepository driftstorningRepository;
  private final DriftstorningarAdapter driftstorningarAdapter;

  public DriftstorningService(DriftstorningRepository driftstorningRepository, DriftstorningarAdapter driftstorningarAdapter) {
    this.driftstorningRepository = driftstorningRepository;
    this.driftstorningarAdapter = driftstorningarAdapter;
  }

  /**
   * Hämtar alla driftstörningar i systemet
   * @return
   */
  public Driftstorningar getAllaDriftstorningar() {
    List<DriftstorningPost> driftstorningPosts = driftstorningRepository.findAll();

    return driftstorningarAdapter.convertDriftstorningPoster(driftstorningPosts);
  }

  /**
   * Filtrerar {@link Driftstorningar} enligt kanaler, marginal och tid
   * @param driftstorningar
   * @param kanaler
   * @param marginalMinuter
   * @param aktuellVidTidpunkt
   * @return
   */
  public Driftstorningar filterDriftstorningar(Driftstorningar driftstorningar, List<String> kanaler, int marginalMinuter, @NotNull LocalDateTime aktuellVidTidpunkt) {
    if(driftstorningar != null) {
      List<Driftstorning> filteredDriftstorningList = driftstorningar.getDriftstorningar().stream()
          .filter(driftstorning -> {
            if(kanaler.isEmpty()) {
              return true;
            }
            for(String kanal : kanaler) {
              if(driftstorning.getKanal().startsWith(kanal)) {
                return true;
              }
            }
            return false;
          })
          .filter(d ->  aktuellVidTidpunkt.isAfter(d.getStart().minusMinutes(marginalMinuter))
              && aktuellVidTidpunkt.isBefore(d.getSlut().plusMinutes(marginalMinuter)))
          .sorted(Comparator.comparing(Driftstorning::getSlut))
          .collect(Collectors.toList());
      driftstorningar.setDriftstorningar(filteredDriftstorningList);
    }
    return driftstorningar;
  }
}
