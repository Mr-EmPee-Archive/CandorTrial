package ml.empee.candortrial.model.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@DatabaseTable(tableName = "users")
public class UserDTO {
  @DatabaseField(id = true)
  private String uuid;
  @DatabaseField
  private String lastChannelId;
}
