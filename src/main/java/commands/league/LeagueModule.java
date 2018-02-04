package commands.league;

import commands.CommandModule;

public class LeagueModule extends CommandModule {




    public LeagueModule() {
        super(null, "");

        //Add all of the available commands
        this.addCommand(new RegisterLeagueUserCommand(this));
        this.addCommand(new RegisterLeagueStatsChannelCommand(this));
    }

    @Override
    protected String getCompleteName() {

        return "";
    }
}
