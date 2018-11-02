package commands.game;

import commands.CommandModule;
import commands.game.elements.ScavengerGameElement;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.Vector;

public class GameModule extends CommandModule {

	public static Vector<ScavengerGameElement> runningGames;
	public static TextChannel gameChannel;

	public GameModule() {
		super(null, "");

		GameModule.runningGames = new Vector<>();

		// Add all of the available commands
		this.addCommand(new AddHint(this));
		this.addCommand(new CreateStandardGame(this));
		this.addCommand(new GetGameList(this));
		this.addCommand(new SetGameChannel(this));
		this.addCommand(new ShowNextHint(this));
		this.addCommand(new ViewGameHintsAdmin(this));
	}

	@Override
	protected String getCompleteName() {

		return "";
	}
}
