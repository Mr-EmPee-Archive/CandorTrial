package ml.empee.candortrial.repositories;

import com.j256.ormlite.dao.DaoManager;
import ml.empee.ioc.Bean;
import ml.empee.candortrial.config.DatabaseConfig;
import ml.empee.candortrial.model.dto.UserDTO;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class UsersRepository extends AbstractRepository<UserDTO, String> implements Bean {
  public UsersRepository(DatabaseConfig config) throws SQLException {
    super(config.getExecutor(), DaoManager.createDao(config.getConnectionSource(), UserDTO.class));
  }

  @Override
  public CompletableFuture<Optional<UserDTO>> findByID(@NotNull String s) {
    return super.findByID(s);
  }

  @Override
  public CompletableFuture<Void> save(@NotNull UserDTO data) {
    return super.save(data);
  }

  @Override
  public CompletableFuture<Void> delete(@NotNull String s) {
    return super.delete(s);
  }
}
