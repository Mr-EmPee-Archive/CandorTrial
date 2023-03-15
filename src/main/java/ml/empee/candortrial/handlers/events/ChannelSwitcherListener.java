package ml.empee.candortrial.handlers.events;

import lombok.RequiredArgsConstructor;
import ml.empee.ioc.Bean;
import ml.empee.ioc.RegisteredListener;
import ml.empee.candortrial.services.ChannelService;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class ChannelSwitcherListener implements Bean, RegisteredListener {

  private final ChannelService channelService;

  public void onJoinSwitchChannel(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    channelService.getUserChannel(player).thenAccept(
      channel -> channelService.setUserChannel(player, channel)
    );
  }

}
