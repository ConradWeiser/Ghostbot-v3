package api.league.data;

public class SummonerInformationElement {

    /**
     * ID of the summoner icon associated with the summoner
     */
    private int profileIconId;

    /**
     * Summoner name
     */
    private String name;

    /**
     * Summoner level associated with the summoner
     */
    private long SummonerLevel;

    /**
     * Date summoner was last modified specified as epoch milliseconds.
     * The following events will update this timestamp: Profile icon change,
     * playing the tutorial or advanced tutorial, finishing a game, summoner name change
     */
    private long revisionDate;

    /**
     * Summoner ID
     */
    private long id;

    /**
     * Account ID
     */
    private long accountId;

    public int getProfileIconId() {
        return profileIconId;
    }

    public String getName() {
        return name;
    }

    public long getSummonerLevel() {
        return SummonerLevel;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public long getId() {
        return id;
    }

    public long getAccountId() {
        return accountId;
    }
}
