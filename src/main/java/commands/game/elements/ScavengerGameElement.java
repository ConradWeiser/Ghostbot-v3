package commands.game.elements;

import commands.game.NoHintAvailableException;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class ScavengerGameElement {

	/**
	 * The queue containing the hints
	 */
	PriorityQueue<String> hintList;
	PriorityQueue<String> completedHints;

	/**
	 * The title of the game
	 */
	String gameName;

	public ScavengerGameElement(String gameName) {
		this.hintList = new PriorityQueue<>();
		this.completedHints = new PriorityQueue<>();
		this.gameName = gameName;
	}

	public void addHint(String hint) {
		hintList.add(hint);
	}

	public String popAndGetNextHint() throws NoHintAvailableException {

		// Verify that there is a hint we can pop
		if(hintList.isEmpty()) {
			throw new NoHintAvailableException();
		}
		else {
			String hint = hintList.remove();
			completedHints.add(hint);

			return hint;
		}
	}

	public List<String> getPreviousHints() {
		return Arrays.asList((String[])completedHints.toArray());
	}

	public List<String> getAllHints() {
		return Arrays.asList(Stream.concat(Arrays.stream(hintList.toArray()), Arrays.stream(completedHints.toArray())).toArray(String[]::new));
	}

	public String getGameName() {
		return gameName;
	}
}
