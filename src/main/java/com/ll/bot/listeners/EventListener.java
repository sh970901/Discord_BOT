package com.ll.bot.listeners;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
public class EventListener extends ListenerAdapter {

    static StringBuilder sb = new StringBuilder();
    static Dotenv config = Dotenv.load();
    static final String url = config.get("url");
    static final String username = config.get("username");
    static final String password = config.get("password");
    static Map<String, Integer> studentMap = new HashMap<>();
    static int classChannel;

    public String buildMention(Map<String, Integer> map, int classChannel) {
        return map.entrySet().stream().filter(e -> classChannel == e.getValue())
                .map(Entry::getKey).collect(Collectors.joining("><@", "<@", ">\n"));
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        input();
        switch (event.getChannel().getId()) {
            case "975680247368384542" -> classChannel = 1;
            case "977133295068389376" -> classChannel = 2;
            case "977133424273928222" -> classChannel = 3;
        }

        if (!event.getAuthor().isBot()) {
            Message msg = event.getMessage();
            if (msg.getContentRaw().equals("!위키")) {
                if(studentMap.size() == 0){
                    event.getChannel().sendMessage("오늘은 위키쓰는 날이 아니에요!").queue();
                    return;
                }
                sb.append("오늘 위키 작성 부탁드립니다!\uD83D\uDE42\n");
                sb.append(buildMention(studentMap, classChannel));
                sb.append("위키 작성 후 작성 완료했다는 메시지 꼭 남겨주세요!!(링크 첨부)");
                event.getChannel().sendMessage(sb.toString()).queue();
                sb.setLength(0);
            }
        }
        sb.setLength(0);
    }

    private void input() {
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "select id,class from class where month =" + month + " AND  day = " + day);
            while (resultSet.next()) {
                studentMap.put(resultSet.getString(1), resultSet.getInt(2));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

































//
//public class EventListener extends ListenerAdapter {
//
//    static StringBuilder sb = new StringBuilder();
//    static Dotenv config = Dotenv.configure().load();
//    static final String url = config.get("url");
//    static final String username = config.get("username");
//    static final String password = config.get("password");
////    static final String password="7497";
////    static final String username ="root";
////    static final String url="jdbc:mysql://localhost:3306/code_lion";
//    static Map<String, Integer> studentMap = new HashMap<>();
//    static int classChannel;
//
//    public String searchStudent(Map<String, Integer> map, int classChannel) {
//        Iterator<String> iter = map.keySet().iterator();
//        List students = new ArrayList();
//        while(iter.hasNext()) {
//            String key = iter.next();
//            int value =map.get(key);
//            if(value == classChannel){
//                students.add(key);
//            }
//        }
//
//        return buildMention(students);
////
////        System.out.println();
////        return map.entrySet().stream().filter(e -> classChannel == e.getValue())
////                .map(Entry::getKey).collect(Collectors.joining("><@", "<@", ">\n"));
//    }
//    public String buildMention(List students){
//        String writer="";
//        for(int i=0; i<students.size(); i++){
//            writer+="<@"+students.get(i)+"> ";
//        }
//        writer+="\n";
//        return writer;
//    }
//
//    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
//        input();
//
//        switch (event.getChannel().getId()) {
//            case "975680247368384542" -> classChannel = 1;
//            case "977133295068389376" -> classChannel = 2;
//            case "977133424273928222" -> classChannel = 3;
//        }
//
//
//
//        sb.append("오늘 위키 작성 부탁드립니다!\uD83D\uDE42\n");
//        sb.append(searchStudent(studentMap, classChannel));
//        sb.append("위키 작성 후 작성 완료했다는 메시지 꼭 남겨주세요!!(링크 첨부)");
//        if (!event.getAuthor().isBot()) {
//            Message msg = event.getMessage();
//            if (msg.getContentRaw().equals("!위키")) {
//                event.getChannel().sendMessage(sb.toString()).queue();
//                sb.setLength(0);
//            }
//        }
//        sb.setLength(0);
//    }
//
//    private void input() {
//        int month = LocalDate.now().getMonthValue();
//        int day = LocalDate.now().getDayOfMonth();
//
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(
//                    "select id,class from class where month =" + month + " AND  day = " + day);
//            while (resultSet.next()) {
//                studentMap.put(resultSet.getString(1), resultSet.getInt(2));
//            }
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
