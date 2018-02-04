package api.league.data;

import java.util.List;

public class MatchDto {

    private int seasonId;
    private int queueId;
    private long gameId;

    private List<ParticipantIdentityDto> participantIdentities;

    private String gameVerison;
    private String platformId;
    private String gameMode;
    private int mapId;
    private String gameType;

    private List<TeamStatsDto> teams;
    private List<ParticipantDto> participants;

    private long gameDuration;
    long gameCreation;

    public int getSeasonId() {
        return seasonId;
    }

    public int getQueueId() {
        return queueId;
    }

    public long getGameId() {
        return gameId;
    }

    public List<ParticipantIdentityDto> getParticipantIdentities() {
        return participantIdentities;
    }

    public String getGameVerison() {
        return gameVerison;
    }

    public String getPlatformId() {
        return platformId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public int getMapId() {
        return mapId;
    }

    public String getGameType() {
        return gameType;
    }

    public List<TeamStatsDto> getTeams() {
        return teams;
    }

    public List<ParticipantDto> getParticipants() {
        return participants;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public long getGameCreation() {
        return gameCreation;
    }
}
