package api.league.throwables;

public class SummonerDoesNotExistException extends Exception {

    private String error = "The League Summoner provided for an arbitrary function does not exist in the League database.";

    public SummonerDoesNotExistException() {

        //Do nothing. Merely allow the object to be created using the default message.
    }

    public SummonerDoesNotExistException(String errorMessage) {

        this.error = errorMessage;
    }

    @Override public String getMessage() {

        return this.error;
    }
}
