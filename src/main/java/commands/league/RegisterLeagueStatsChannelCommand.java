package commands.league;

import commands.Command;
import commands.CommandModule;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sql.interfaces.SqlGenericInterface;
import sql.interfaces.SqlUserInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterLeagueStatsChannelCommand extends Command {

    public RegisterLeagueStatsChannelCommand(CommandModule module) {

        super("designate league stats channel", module);
    }



    @Override
    protected void doCommand(MessageReceivedEvent event) {

        //Verify that the user calling this command is a discord administrator
        SqlUserInterface sqlUserInterface = new SqlUserInterface();

        if(!sqlUserInterface.isUserAdmin(event.getAuthor().getId())) {

            //Alert the user
            event.getChannel().sendMessage("Only administrators can run this command.").queue();
            return;
        }

        //Verify that this isn't being sent in a private channel.
        if(event.getChannelType() != ChannelType.TEXT) {

            event.getChannel().sendMessage("This command can only be run in a guild text channel.").queue();
            return;
        }

        //Verify that there are currently no other channels present for the given guild
        SqlGenericInterface sql = new SqlGenericInterface();
        String verificationQuery = "SELECT * FROM league_of_legends_stat_channels WHERE guild_id = \""
                + event.getGuild().getId() + "\"";

        ResultSet verificationResult = sql.executeSelectStatement(verificationQuery);

        //If there currently is a guild with a stat channel, delete the entry unless it matches the current ID
        if(!sql.isResultSetEmpty(verificationResult)) {

            //Check that the database ID does not match the Guild Channel ID
            try {

                if(verificationResult.getString(2).equals(event.getChannel().getId())) {

                    //Alert the user if they match, and return
                    event.getChannel().sendMessage("This channel is already registered as a stat-dump channel!").queue();
                    return;
                }
            } catch (SQLException e) {

                //TODO: Implement bot logger error handling
                e.printStackTrace();

            }

            //Otherwise, the channel needs to be deleted, and replaced with the current channel.
            String deleteQuery = "DELETE FROM league_of_legends_stat_channels WHERE guild_id = \""
                    + event.getGuild().getId() + "\"";

            sql.executeUpdateStatement(deleteQuery);

            //Insert the new channel into the database
            String insertQuery = "INSERT INTO league_of_legends_stat_channels (guild_id, channel_id) VALUES (\""
                    + event.getGuild().getId() + "\",\"" + event.getChannel().getId() + "\")";

            sql.executeInsertStatement(insertQuery);

            //Alert the user
            event.getChannel().sendMessage("Registered channel ID `" + event.getChannel().getId() + "` as a League stat channel!").queue();

        }


    }
}
