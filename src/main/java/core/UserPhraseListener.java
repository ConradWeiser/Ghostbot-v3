package core;

import commands.game.GameModule;
import commands.game.elements.ScavengerGameElement;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class UserPhraseListener extends ListenerAdapter {

	// Check to see if the user has uttered a solution to one of the games
	@Override public void onMessageReceived(MessageReceivedEvent event) {

		for(ScavengerGameElement element : GameModule.runningGames) {
			if(event.getMessage().getContentRaw().toLowerCase().equals(element.getEndPhrase().toLowerCase())) {
				// Winner winner chicken dinner!
				StringBuilder builder = new StringBuilder();
				builder.append(event.getAuthor().getName()).append(" has just completed ").append(element.getGameName()).append("! Congrats!");
				GameModule.gameChannel.sendMessage(builder.toString()).queue();
			}
		}
	}
}
