package se.mdh.driftstorning.common.model;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "kanaler")
public class KanalPost {

  @Id
  private String id;

  @NotNull(message = "Fältet måste ha ett värde")
  @Indexed(unique = true)
  private String namn;

  @NotNull(message = "Fältet måste ha ett värde")
  private String text;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNamn() {
    return namn;
  }

  public void setNamn(String namn) {
    this.namn = namn;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
