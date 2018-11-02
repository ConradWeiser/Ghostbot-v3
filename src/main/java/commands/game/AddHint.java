package commands.game;

import commands.Command;
import commands.CommandModule;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sql.interfaces.SqlUserInterface;

import java.util.List;

public class AddHint extends Command {

	public AddHint(CommandModule module) {
		super("addhint", module);
	}

	@Override
	protected void doCommand(MessageReceivedEvent event) {

		// Is the user admin?
		SqlUserInterface sql = new SqlUserInterface();
		if(!sql.isUserAdmin(event.getAuthor().getId())) {
			event.getTextChannel().sendMessage("You're not an admin. Please stahp.").queue();
			return;
		}

		// Get the game argument
		List<String> args = this.getArguments(event.getMessage(), 8);
		if(args.isEmpty()) {
			event.getTextChannel().sendMessage("Please specify a game you're adding a hint for").queue();
			return;
		}

		int gameIndex = Integer.valueOf(args.get(0));
		String hint = args.get(1);

		if(GameModule.runningGames.size() < gameIndex) {
			event.getTextChannel().sendMessage("This game does not exist.").queue();
			return;
		}

		// Do the thing now
		GameModule.runningGames.get(gameIndex).addHint(hint);

		event.getTextChannel().sendMessage("Hint added!").queue();
		return;

	}
}
