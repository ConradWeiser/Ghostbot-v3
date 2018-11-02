package commands.game;

import commands.Command;
import commands.CommandModule;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sql.interfaces.SqlUserInterface;

public class SetGameChannel extends Command {

	public SetGameChannel(CommandModule module) {
		super("setgamechannel", module);
	}

	@Override
	protected void doCommand(MessageReceivedEvent event) {

		SqlUserInterface sql = new SqlUserInterface();

		// Admin pls
		if(!sql.isUserAdmin(event.getAuthor().getId())) {
			event.getTextChannel().sendMessage("Be discord admin pls").queue();
			return;
		}

		GameModule.gameChannel = event.getTextChannel();
		GameModule.gameChannel.sendMessage("Channel registered as the bot game channel!").queue();
	}
}
