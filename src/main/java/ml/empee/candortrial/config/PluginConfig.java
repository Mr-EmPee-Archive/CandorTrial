package ml.empee.candortrial.config;

import lombok.Getter;
import ml.empee.configurator.Configuration;
import ml.empee.configurator.annotations.Path;
import ml.empee.ioc.Bean;
import ml.empee.candortrial.model.entities.Channel;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Getter
public class PluginConfig extends Configuration implements Bean {

  @Path("channels")
  private List<Channel> channels;

  public PluginConfig(JavaPlugin plugin) {
    super(plugin, "config.yml", 1);
  }

  private void setChannels(List<String> channels) {
    this.channels = channels.stream()
      .map(Channel::of)
      .toList();
  }

}
