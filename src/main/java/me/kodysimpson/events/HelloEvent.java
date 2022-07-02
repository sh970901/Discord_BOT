package me.kodysimpson.events;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class HelloEvent extends ListenerAdapter  {

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        String url = "jdbc:mysql://localhost:3306/code_lion";
        String userName = "root";
        String password = "password";

        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        String[] students = new String[3];

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "select * from class1 where month =" + month + " AND  day = " + day);
            int i = 0;
            while (resultSet.next()) {
                students[i++] = resultSet.getString(2);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String > members = new ArrayList<>();
//        for (int i = 0; i < students.length; i++) {
        String asMention = event.getGuild().getMembersByNickname(students[0], true).get(0)
                .getAsMention();
//        }
//        System.out.println(effectiveName);
        System.out.println(asMention);
        if (!event.getAuthor().isBot()) {
            Message msg = event.getMessage();
            if (msg.getContentRaw().equals("!위키")) {
//                event.
               event.getChannel().sendMessage("<#" + asMention + '>').queue();
            }
        }
    }
}


