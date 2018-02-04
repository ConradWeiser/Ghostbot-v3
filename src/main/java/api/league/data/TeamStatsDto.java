package api.league.data;

import java.util.List;

public class TeamStatsDto {

    private boolean firstDragon;
    private boolean firstInhibitor;

    private List<TeamBansDto> bans;
    private int baronKills;
    private boolean firstRiftHerald;
    private boolean firstBaron;
    private int riftHeraldKills;
    private boolean firstBlood;
    private int teamId;
    private boolean firstTower;
    private int vilemawKills;
    private int inhibitorKills;
    private int towerKills;
    private int dominationVictoryScore;
    private String win;
    private int dragonKills;

    public boolean isFirstDragon() {
        return firstDragon;
    }

    public boolean isFirstInhibitor() {
        return firstInhibitor;
    }

    public List<TeamBansDto> getBans() {
        return bans;
    }

    public int getBaronKills() {
        return baronKills;
    }

    public boolean isFirstRiftHerald() {
        return firstRiftHerald;
    }

    public boolean isFirstBaron() {
        return firstBaron;
    }

    public int getRiftHeraldKills() {
        return riftHeraldKills;
    }

    public boolean isFirstBlood() {
        return firstBlood;
    }

    public int getTeamId() {
        return teamId;
    }

    public boolean isFirstTower() {
        return firstTower;
    }

    public int getVilemawKills() {
        return vilemawKills;
    }

    public int getInhibitorKills() {
        return inhibitorKills;
    }

    public int getTowerKills() {
        return towerKills;
    }

    public int getDominationVictoryScore() {
        return dominationVictoryScore;
    }

    public String getWin() {
        return win;
    }

    public int getDragonKills() {
        return dragonKills;
    }
}
