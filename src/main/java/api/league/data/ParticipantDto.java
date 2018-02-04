package api.league.data;

import java.util.List;

public class ParticipantDto {

    private ParticipantStatsDto stats;
    private List<RuneDto> runes;
    private int participantId;
    private int teamId;
    private int spell2Id;
    private List<MasteryDto> masteries;
    private String highestAchievedSeasonTeir;
    private int spell1Id;
    private int championId;

    public ParticipantStatsDto getStats() {
        return stats;
    }

    public List<RuneDto> getRunes() {
        return runes;
    }

    public int getParticipantId() {
        return participantId;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getSpell2Id() {
        return spell2Id;
    }

    public List<MasteryDto> getMasteries() {
        return masteries;
    }

    public String getHighestAchievedSeasonTeir() {
        return highestAchievedSeasonTeir;
    }

    public int getSpell1Id() {
        return spell1Id;
    }

    public int getChampionId() {
        return championId;
    }
}
