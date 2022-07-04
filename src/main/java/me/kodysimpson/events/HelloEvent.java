package me.kodysimpson.events;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
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
//        String[] students = new String[3];

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Integer> students= new HashMap<>();
        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "select discord,class from class1 where month =" + month + " AND  day = " + day);
            int i = 0;
            while (resultSet.next()) {
                students.put(resultSet.getString(1),resultSet.getInt(2));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] class1 = new String[3];
        String[] class2 = new String[3];
        String[] class3 = new String[3];

        int i=0,j=0,k=0;

        for(Map.Entry<String,Integer> entry : students.entrySet()){
            if(entry.getValue().equals(1)){
                class1[i++]=entry.getKey();
            }
            if(entry.getValue().equals(2)){
                class2[j++]=entry.getKey();
            }
            if(entry.getValue().equals(3)){
                class3[k++]=entry.getKey();
            }
        }

        String botId = "992099998240079932";
//        String message = "오늘 위키 작성 부탁드립니다!\uD83D\uDE42\n";
//        message += "<@" + class1[0] + "> <@" + class1[1] + "> <@" + class1[2] + "> \n";
//        message += "<@" + class2[0] + "> <@" + class2[1] + "> <@" + class1[2] + "> \n";
//        message += "<@" + class3[0] + "> <@" + class3[1] + "> <@" + class3[2] + "> \n";
//        message += "위키 작성 후 작성 완료했다는 메시지 꼭 남겨주세요!!(링크 첨부)";
        String message1 = "<@" + class1[0] + "> <@" + class1[1] + "> <@" + class1[2] + "> \n";
        String message2 = "<@" + class2[0] + "> <@" + class2[1] + "> <@" + class1[2] + "> \n";
        String message3 = "<@" + class3[0] + "> <@" + class3[1] + "> <@" + class3[2] + "> \n";
        if (!event.getAuthor().isBot()) {
            Message msg = event.getMessage();
            if(msg.getContentRaw().equals("!위키")){
                if(event.getChannel().getId().equals("993536820098899999")){
                    event.getChannel().sendMessage( message1 ).queue();
                }
                if(event.getChannel().getId().equals("993536864529166337")){
                    event.getChannel().sendMessage( message2 ).queue();
                }
                if(event.getChannel().getId().equals("993536879838380154")){
                    event.getChannel().sendMessage( message3 ).queue();
                }

            }
        }
    }
}


