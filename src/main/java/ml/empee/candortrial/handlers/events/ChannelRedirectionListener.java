package ml.empee.candortrial.handlers.events;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ml.empee.candortrial.model.entities.Channel;
import ml.empee.candortrial.services.ChannelService;
import ml.empee.ioc.Bean;
import ml.empee.ioc.RegisteredListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@RequiredArgsConstructor
public class ChannelRedirectionListener implements Bean, RegisteredListener {

  private final ChannelService channelService;

  @SneakyThrows
  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled=true)
  public void redirectPlayerChat(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    Channel channel = channelService.getUserChannel(player).get();
    var iterator = event.getRecipients().iterator();
    while (iterator.hasNext()) {
      Channel targetChannel = channelService.getUserChannel(iterator.next()).get();
      if(!targetChannel.equals(channel)) {
        iterator.remove();
      }
    }
  }

}
