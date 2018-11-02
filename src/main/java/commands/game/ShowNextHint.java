package commands.game;

import commands.Command;
import commands.CommandModule;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sql.interfaces.SqlUserInterface;

import java.awt.*;
import java.util.List;

public class ShowNextHint extends Command {

	public ShowNextHint(CommandModule module) {
		super("pophint", module);
	}

	@Override
	protected void doCommand(MessageReceivedEvent event) {

		// Is the user admin?
		SqlUserInterface sql = new SqlUserInterface();
		if(!sql.isUserAdmin(event.getAuthor().getId())) {
			event.getTextChannel().sendMessage("Please be an admin before you try this :((((").queue();
			return;
		}

		int num = Integer.valueOf(event.getMessage().getContentRaw().substring(9));

		// Show the hint in the game channel
		try {
			//GameModule.gameChannel.sendMessage(GameModule.runningGames.get(num).popAndGetNextHint()).queue();
			String hint = GameModule.runningGames.get(num).popAndGetNextHint();
			EmbedBuilder builder = new EmbedBuilder();

			builder.setColor(Color.RED);
			builder.setTitle("New Hint");
			builder.setDescription(GameModule.runningGames.get(num).getGameName());
			builder.addField("Content", hint, false);

			GameModule.gameChannel.sendMessage(builder.build()).queue();

		} catch (NoHintAvailableException e) {
			event.getTextChannel().sendMessage("There is no remaining hints to show for this game").queue();
			return;
		}

	}
}
