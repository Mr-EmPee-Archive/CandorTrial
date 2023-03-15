package ml.empee.candortrial;

import lombok.Getter;
import ml.empee.ioc.SimpleIoC;
import ml.empee.candortrial.config.DatabaseConfig;
import org.bukkit.plugin.java.JavaPlugin;

/** Boot class of this plugin. **/

public final class CandorTrial extends JavaPlugin {

  public static final String PREFIX = "  &5CT &8»&r ";

  private final DatabaseConfig databaseConfig = new DatabaseConfig(this);

  @Getter
  private final SimpleIoC iocContainer = new SimpleIoC(this);

  public void onEnable() {
    iocContainer.addBean(databaseConfig);
    iocContainer.initialize("relocations");
  }

  public void onDisable() {
    iocContainer.removeAllBeans();
    databaseConfig.closeConnection();
  }
}
