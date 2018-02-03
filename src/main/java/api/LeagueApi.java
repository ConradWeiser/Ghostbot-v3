package api;

import api.core.ApiMethod;
import api.core.request.RequestManager;
import api.core.request.exceptions.ApiConnectionFailureException;
import api.league.data.SummonerInformationElement;
import api.league.methods.GetSummonerInformation;
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
    public SummonerInformationElement getSummonerInformation(long summonerId) throws ApiConnectionFailureException, SummonerDoesNotExistException {

        ApiMethod method = new GetSummonerInformation(summonerId);
        SummonerInformationElement element = requestManager.makeApiRequest(method);

        //TODO: Do a less sketchy way of checking if the summoner exists. Currently, we're just seeing if the name is empty. Maybe do something with response codes?
        if(element.getName().isEmpty())
            throw new SummonerDoesNotExistException();

        //Otherwise, return the element
        return element;
    }

}