package api.league.data;

public class PlayerDto {

    private String currentPlatformId;
    private String summonerName;
    private String matchHistoryUri;
    private String platformId;
    private long currentAccountId;
    private int profileIcon;
    private long summonerId;


    public String getCurrentPlatformId() {
        return currentPlatformId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public String getMatchHistoryUri() {
        return matchHistoryUri;
    }

    public String getPlatformId() {
        return platformId;
    }

    public long getCurrentAccountId() {
        return currentAccountId;
    }

    public int getProfileIcon() {
        return profileIcon;
    }

    public long getSummonerId() {
        return summonerId;
    }
}
