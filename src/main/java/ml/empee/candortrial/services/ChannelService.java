package ml.empee.candortrial.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.RequiredArgsConstructor;
import ml.empee.ioc.Bean;
import ml.empee.candortrial.config.PluginConfig;
import ml.empee.candortrial.model.dto.UserDTO;
import ml.empee.candortrial.model.entities.Channel;
import ml.empee.candortrial.repositories.UsersRepository;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class ChannelService implements Bean {

  private final JavaPlugin plugin;
  private final PluginConfig pluginConfig;
  private final UsersRepository usersRepository;
  private final Cache<UUID, Channel> channelCache = CacheBuilder.newBuilder()
    .expireAfterAccess(10, TimeUnit.MINUTES)
    .maximumSize(200)
    .build();

  public Optional<Channel> getChannelById(String id) {
    if(id.equalsIgnoreCase("general")) {
      return Optional.of(Channel.DEFAULT);
    }

    return pluginConfig.getChannels().stream()
      .filter(c -> c.getId().equalsIgnoreCase(id))
      .findFirst();
  }

  public void setUserChannel(Player player, Channel channel) {
    channelCache.put(player.getUniqueId(), channel);

    if(channel.equals(Channel.DEFAULT)) {
      usersRepository.delete(player.getUniqueId().toString());
    } else {
      usersRepository.save(UserDTO.of(player.getUniqueId().toString(), channel.getId()));
    }
  }

  public CompletableFuture<Channel> getUserChannel(Player player) {
    Channel channel = channelCache.getIfPresent(player.getUniqueId());
    if (channel != null) {
      return CompletableFuture.completedFuture(channel);
    }

    return usersRepository.findByID(player.getUniqueId().toString())
      .thenApply(user -> {
        var result = user.flatMap(userDTO -> getChannelById(userDTO.getLastChannelId()));
        channelCache.put(player.getUniqueId(), result.orElse(Channel.DEFAULT));
        return result.orElse(Channel.DEFAULT);
      });
  }

}
