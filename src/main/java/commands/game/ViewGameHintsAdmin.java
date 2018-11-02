package commands.game;

import commands.Command;
import commands.CommandModule;
import commands.game.elements.ScavengerGameElement;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sql.interfaces.SqlUserInterface;

import java.awt.*;
import java.util.List;

public class ViewGameHintsAdmin extends Command {

	public ViewGameHintsAdmin(CommandModule module) {
		super("viewhintsadmin", module);
	}

	@Override
	protected void doCommand(MessageReceivedEvent event) {

		// You better be an admin or I'm kicking your shit in
		SqlUserInterface sql = new SqlUserInterface();

		if(!sql.isUserAdmin(event.getAuthor().getId())) {
			event.getTextChannel().sendMessage("Get your non-admin butt out of here! No cheating plz").queue();
			return;
		}

		// Get which current running game the hints should be displayed for
		int num = Integer.valueOf(event.getMessage().getContentRaw().substring(16));

			ScavengerGameElement currGame = GameModule.runningGames.get(num);

			// Create the embed here
			EmbedBuilder builder = new EmbedBuilder();
			builder.setColor(Color.GREEN);
			builder.setTitle("Available hints");

			for(String hint : currGame.getAllHints()) {
				builder.addField("Hint", hint, false);
			}

			event.getTextChannel().sendMessage(builder.build()).queue();
	}

}
