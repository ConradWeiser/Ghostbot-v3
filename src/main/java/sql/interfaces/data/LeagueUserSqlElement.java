package sql.interfaces.data;

public class LeagueUserSqlElement {

    private String summonerName;
    private long summoner_id;

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public long getSummoner_id() {
        return summoner_id;
    }

    public void setSummoner_id(long summoner_id) {
        this.summoner_id = summoner_id;
    }
}
