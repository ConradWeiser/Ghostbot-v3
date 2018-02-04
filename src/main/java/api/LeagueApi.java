package api;

import api.core.ApiMethod;
import api.core.request.RequestManager;
import api.core.request.exceptions.ApiConnectionFailureException;
import api.league.data.MatchDto;
import api.league.data.MatchlistDto;
import api.league.data.SummonerInformationElement;
import api.league.methods.GetMatchById;
import api.league.methods.GetSummonerInformationById;
import api.league.methods.GetSummonerInformationByName;
import api.league.methods.GetSummonerMatchHistory;
import api.league.throwables.MatchDoesNotExistException;
import api.league.throwables.SummonerDoesNotExistException;

public class LeagueApi {

    /**
     * Request Manager for the League API
     */
    private static RequestManager requestManager = null;

    /**
     * Singleton implementation of the LeagueApi class, thus allowing ratelimiting to work properly.
     */
    private static LeagueApi instance = null;

    public static LeagueApi getInstance() {

        if(LeagueApi.instance == null) {
            LeagueApi.instance = new LeagueApi();
        }

        return LeagueApi.instance;
    }


    private LeagueApi() {

        //On being called, create the RequestManager for this part of the API
        this.requestManager = new RequestManager();

    }

    /**
     * Method returning a {@link SummonerInformationElement} given a specified summoner ID.
     * @throws ApiConnectionFailureException When the API doesn't get a proper response from the endpoint
     * @throws api.league.throwables.SummonerDoesNotExistException When the API returns that there is no summoner with that ID
     */
    public SummonerInformationElement getSummonerInformationById(long summonerId) throws ApiConnectionFailureException, SummonerDoesNotExistException {

        ApiMethod method = new GetSummonerInformationById(summonerId);
        SummonerInformationElement element = requestManager.makeApiRequest(method);

        //TODO: Do a less sketchy way of checking if the summoner exists. Currently, we're just seeing if the name is empty. Maybe do something with response codes?
        if(element.getName().isEmpty())
            throw new SummonerDoesNotExistException();

        //Otherwise, return the element
        return element;
    }

    /**
     * Method returning a {@link SummonerInformationElement} given a specified summoner name.
     * @throws ApiConnectionFailureException When the API doesn't get a proper response from the endpoint
     * @throws api.league.throwables.SummonerDoesNotExistException When the API returns that there is no summoner with that ID
     */
    public SummonerInformationElement getSummonerInformationByName(String summonerName) throws SummonerDoesNotExistException, ApiConnectionFailureException {

        ApiMethod method = new GetSummonerInformationByName(summonerName);

        SummonerInformationElement element = requestManager.makeApiRequest(method);

        //TODO: Less sketchy way of checking if a summoner exists. Same as above method
        if(element.getName() == null)
            throw new SummonerDoesNotExistException();

        //Otherwise, return the element
        return element;
    }

    public MatchlistDto getSummonerMatchHistoryById(long summonerId) {

        //TODO: All of this
        return null;
    }

    public MatchDto getMatchInformationByMatchId(String matchId) throws ApiConnectionFailureException, MatchDoesNotExistException {

        ApiMethod method = new GetMatchById(matchId);

        MatchDto element = requestManager.makeApiRequest(method);

        //TODO: Do a better way of doing this
        if(element.getGameMode() == null)
            throw new MatchDoesNotExistException();

        return element;
    }

}
