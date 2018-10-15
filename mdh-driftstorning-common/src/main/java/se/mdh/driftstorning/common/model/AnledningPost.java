package se.mdh.driftstorning.common.model;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "anledningar")
public class AnledningPost {

  @Id
  private String id;

  @NotEmpty(message = "Fältet måste ha ett värde")
  @Indexed(unique = true)
  private String namn;

  @NotEmpty(message = "Fältet måste ha ett värde")
  private String text;

  private String meddelandeSv;
  private String meddelandeEn;
}
