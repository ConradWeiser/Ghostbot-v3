package commands.league;

import api.LeagueApi;
import api.core.request.exceptions.ApiConnectionFailureException;
import api.league.data.SummonerInformationElement;
import api.league.throwables.SummonerDoesNotExistException;
import commands.Command;
import commands.CommandModule;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sql.interfaces.SqlLeagueInterface;

public class RegisterLeagueUserCommand extends Command {


    /**
     * Constructor that requires the name of the command
     */
    public RegisterLeagueUserCommand(CommandModule module) {
        super("league register", module);
    }

    @Override
    protected void doCommand(MessageReceivedEvent event) {

        //Only allow this to be run in a guild channel.
        if(event.getChannelType() != ChannelType.TEXT) {

            event.getChannel().sendMessage("Please run this in a guild with a registered stats channel. Aborting.").queue();
            return;
        }

        //Pull the League Summoner name argument using substring. No need to get all fancy with static command names.
        //TODO: implement a warning if the user doesn't give a username. Right now it just errors out due to the substring below
        String summonerName = event.getMessage().getContentRaw().substring(17);

        //Query the League API for the information regarding the summoner
        LeagueApi api = LeagueApi.getInstance();
        SummonerInformationElement element = null;

        try {

            element = api.getSummonerInformationByName(summonerName);

        } catch (ApiConnectionFailureException e) {

            //If there was an error communicating with the Discord Servers
            event.getTextChannel().sendMessage("Failed to connect to League servers. Please try again later!").queue();
            return;

        } catch (SummonerDoesNotExistException e) {

            //There was no summoner with that name.
            StringBuilder responseBuilder = new StringBuilder();
            responseBuilder.append("There was no summoner with the name: `");
            responseBuilder.append(summonerName);
            responseBuilder.append("` | Please check your spelling and try again!");

            event.getTextChannel().sendMessage(responseBuilder.toString()).queue();
            return;

        }

        SqlLeagueInterface sqlInterface = new SqlLeagueInterface();

        //Verify that there is a league channel for the the guild the user is speaking in.
        if(sqlInterface.leagueStatChannelExistsForGivenGuild(event.getGuild().getId())) {

            //If not, alert the user
            event.getChannel().sendMessage("Please contact a GhostBot admin user to set a league stat channel for this guild first.").queue();
            return;
        }

        //Update the SQL database with the supplied information
        sqlInterface.registerLeagueUser(element.getName(), element.getAccountId(), event.getAuthor().getId(), event.getGuild().getId());

        //Parse the element for the summoner information, and print it out for verification for the user
        StringBuilder builder = new StringBuilder();
        builder.append("```Summoner Information Registered\n------------------------\nSummoner Name: ");
        builder.append(element.getName());
        builder.append("\nSummoner Level: ");
        builder.append(element.getSummonerLevel());
        builder.append(" -- I'm flaming. That number totally isn't because there's a programming mistake.\nSummoner Account ID: ");
        builder.append(element.getAccountId());
        builder.append("```");

        //Queue the message to be sent to the user
        if(event.getChannelType().isGuild()) {
            event.getTextChannel().sendMessage(builder.toString()).queue();
        }

        else {
            event.getPrivateChannel().sendMessage(builder.toString()).queue();
        }


    }
}
