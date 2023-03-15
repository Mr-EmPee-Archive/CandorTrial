package ml.empee.candortrial.controllers;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import lombok.RequiredArgsConstructor;
import ml.empee.candortrial.Permissions;
import ml.empee.candortrial.config.CommandsConfig;
import ml.empee.candortrial.services.ChannelService;
import ml.empee.candortrial.utils.Logger;
import ml.empee.ioc.Bean;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class PluginController implements Bean {

  private final CommandsConfig commandsConfig;
  private final ChannelService channelService;

  @Override
  public void onStart() {
    commandsConfig.register(this);
  }

  @CommandMethod("switchchannel <channel>")
  public void switchChannel(
    Player sender, @Argument String channel
  ) {
    if(!sender.hasPermission(Permissions.CHANNEL_ACCESS + channel.toLowerCase())) {
      Logger.log(sender, "&cYou don't have enough permissions");
      return;
    }

    var parsedChannel = channelService.getChannelById(channel);
    if(parsedChannel.isEmpty()) {
      Logger.log(sender, "&cThe channel &e%s&c doesn't exists!", channel);
      return;
    }

    channelService.setUserChannel(sender, parsedChannel.get());
    Logger.log(sender, "&eSwitched to channel &c%s", channel.toLowerCase());
  }

}
