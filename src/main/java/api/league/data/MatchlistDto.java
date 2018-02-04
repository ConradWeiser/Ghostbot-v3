package api.league.data;

import java.util.List;

public class MatchlistDto {

    private List<MatchReferenceDto> matches;
    private int totalGames;
    private int startIndex;
    private int endIndex;

    public List<MatchReferenceDto> getMatches() {
        return matches;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
