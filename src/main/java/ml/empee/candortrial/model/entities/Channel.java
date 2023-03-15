package ml.empee.candortrial.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Channel {

  public static final Channel DEFAULT = Channel.of(null);

  private final String id;
}
