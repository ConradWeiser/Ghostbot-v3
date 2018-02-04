package sql.interfaces;

import sql.exceptions.DataNotExistsException;
import sql.interfaces.data.LeagueUserSqlElement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlLeagueInterface extends SqlGenericInterface {

    public SqlLeagueInterface() {

        //Invoke the parent constructors to prepare the SQLConnector
        super();
    }

    public boolean leagueStatChannelExistsForGivenGuild(String guildId) {

        //Build the SQL query
        String query = "SELECT * FROM league_of_legends_stat_channels WHERE guild_id = " + guildId;
        ResultSet result = this.executeSelectStatement(query);

        //If the set is empty, one does not exist. Otherwise, one does.
        if(this.isResultSetEmpty(result))
            return false;

        else
            return true;
    }

    public void registerLeagueUser(String summonerName, long summonerId, String discordUserId, String guildId) {

        //Build the SQL query
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO league_of_legends_users (summoner_name, summoner_id, affiliated_discord_id, home_discord_guild_id) VALUES (\"");
        queryBuilder.append(summonerName);
        queryBuilder.append("\",");
        queryBuilder.append(summonerId);
        queryBuilder.append(", \"");
        queryBuilder.append(discordUserId);
        queryBuilder.append("\",\"");
        queryBuilder.append(guildId);
        queryBuilder.append("\")");

        //TODO: Remove debug line
        System.out.println(queryBuilder.toString());

        //Execute SQL statement
        this.executeInsertStatement(queryBuilder.toString());
    }

    public LeagueUserSqlElement getLeagueUserElementByDiscordId(String discordUserId) throws DataNotExistsException {

        //Build the SQL query
        String query = "SELECT * FROM league_of_legends_users WHERE affiliated_discord_id = " + discordUserId;

        ResultSet result = this.executeSelectStatement(query);

        //If the result is empty, throw an error
        if(this.isResultSetEmpty(result))
            throw new DataNotExistsException("There is no league user for the given discord ID");

        //Otherwise, package the ResultSet into it's correct object
        LeagueUserSqlElement element = new LeagueUserSqlElement();

        try {
            element.setSummonerName(result.getString(1));
            element.setSummoner_id(result.getLong(2));

        } catch (SQLException e) {
            //You should never see this at this point.
            e.printStackTrace();
        }

        return element;

    }
}
