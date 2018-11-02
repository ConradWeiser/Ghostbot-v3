package commands.game;

import commands.Command;
import commands.CommandModule;
import commands.game.elements.ScavengerGameElement;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class CreateStandardGame extends Command {

	public CreateStandardGame(CommandModule module) {
		super("creategame", module);
	}

	@Override
	protected void doCommand(MessageReceivedEvent event) {

		List<String> args = this.getArguments(event.getMessage(), 11);

		// We should be looking for just one argument
		if(args.size() < 1) {
			event.getTextChannel().sendMessage("Please give a title with the command!").queue();
			return;
		}

		else {
			GameModule.runningGames.add(new ScavengerGameElement(args.get(0), args.get(1)));
			event.getTextChannel().sendMessage("Event has been created.").queue();
			return;
		}
	}
}
