package commands.game;

import commands.Command;
import commands.CommandModule;
import commands.game.elements.ScavengerGameElement;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.Vector;

public class GetGameList extends Command {

	public GetGameList(CommandModule module) {
		super("gamelist", module);
	}


	@Override
	protected void doCommand(MessageReceivedEvent event) {

		Vector<ScavengerGameElement> games = GameModule.runningGames;
		if(games.isEmpty()) {
			event.getTextChannel().sendMessage("There are no games running").queue();
			return;
		}

		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < games.size(); i++) {
			builder.append(i).append(")").append(" ").append(games.get(i).getGameName()).append("\n");
		}

		event.getTextChannel().sendMessage(builder.toString()).queue();

	}
}
