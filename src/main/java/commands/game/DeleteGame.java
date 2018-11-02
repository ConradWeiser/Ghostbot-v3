package commands.game;

import commands.Command;
import commands.CommandModule;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sql.interfaces.SqlUserInterface;

public class DeleteGame extends Command {

	public DeleteGame(CommandModule module) {
		super("deletegame", module);
	}

	@Override
	protected void doCommand(MessageReceivedEvent event) {

		// Is the user admin?
		SqlUserInterface sql = new SqlUserInterface();

		if(!sql.isUserAdmin(event.getAuthor().getId())) {
			event.getTextChannel().sendMessage("Pls be admin. Also noooooo").queue();
			return;
		}

		int arg = Integer.valueOf(event.getMessage().getContentRaw().substring(12));

		GameModule.runningGames.remove(arg);
		event.getTextChannel().sendMessage("Game removed").queue();

	}
}
