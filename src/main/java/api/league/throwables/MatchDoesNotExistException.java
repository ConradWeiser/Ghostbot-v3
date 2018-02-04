package api.league.throwables;

public class MatchDoesNotExistException extends Exception {

    String error = "";

    public MatchDoesNotExistException() {

        error = "Requested match by ID does not exist";
    }

    public MatchDoesNotExistException(String message) {

        this.error = message;
    }

    @Override public String getMessage() {

        return this.error;
    }
}
