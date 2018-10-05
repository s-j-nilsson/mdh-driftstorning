package se.mdh.driftstorning.service.adapter;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import se.mdh.driftstorning.common.model.DriftstorningPost;
import se.mdh.driftstorning.service.model.Driftstorning;
import se.mdh.driftstorning.service.model.Driftstorningar;

@Component
public class DriftstorningarAdapter {
  private static final Log log = LogFactory.getLog(DriftstorningarAdapter.class);

  private DriftstorningAdapter driftstorningAdapter;

  public DriftstorningarAdapter(DriftstorningAdapter driftstorningAdapter) {
    this.driftstorningAdapter = driftstorningAdapter;
  }

  public Driftstorningar convertDriftstorningPoster(List<DriftstorningPost> driftstorningPosts) {
    Driftstorningar driftstorningar = new Driftstorningar();
    List<Driftstorning> driftstorningList = new ArrayList<>();
    for(DriftstorningPost driftstorningPost : driftstorningPosts) {
     driftstorningList.add(driftstorningAdapter.convertDriftstorningPost(driftstorningPost));
    }
    driftstorningar.setDriftstorningar(driftstorningList);
    return driftstorningar;
  }
}
