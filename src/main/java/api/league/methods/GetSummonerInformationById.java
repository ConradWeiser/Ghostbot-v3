package api.league.methods;

import api.core.parameters.HttpHeaderParameter;
import api.core.request.RequestMethod;
import api.league.LeagueApiManager;
import api.league.data.SummonerInformationElement;

public class GetSummonerInformationById extends LeagueApiManager {

    public GetSummonerInformationById(long userId) {

        this.setUrlBase("https://na1.api.riotgames.com/lol/summoner/v3/summoners/" + userId);

        //Add the correct headers into the request
        this.addLeagueApiKeyToRequest();
        this.addHttpHeaderParameter(new HttpHeaderParameter("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8"));

        //This method is to be ran as GET
        this.setMethod(RequestMethod.GET);

        //Declare what the response should look like
        this.setReturnType(SummonerInformationElement.class);


    }
}
