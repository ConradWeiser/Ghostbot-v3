package api.league;

import api.core.ApiMethod;
import api.core.parameters.HttpHeaderParameter;
import core.BotConfigurationManager;
import core.enums.ConfigurationVariable;

public class LeagueApiManager extends ApiMethod {

    BotConfigurationManager configManager = null;

    protected LeagueApiManager() {

        super("league");

        //Populate the configuration manager with the current bot interface
        this.configManager = BotConfigurationManager.getInstance();

    }

    protected void addLeagueApiKeyToRequest() {

        //Get the current League API key saved in the configuration
        String leagueToken = this.configManager.getPropertyValue(ConfigurationVariable.LEAGUE_OF_LEGENDS_API_KEY);
        this.addHttpHeaderParameter(new HttpHeaderParameter("X-Riot-Token", leagueToken));
    }

    //TODO: Manage ratelimiting here. Probably some sort of threaded queue system.
}
