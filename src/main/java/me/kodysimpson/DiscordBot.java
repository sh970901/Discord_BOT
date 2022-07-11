//package me.kodysimpson;
//
//
//import me.kodysimpson.events.HelloEvent;
//import net.dv8tion.jda.api.JDA;
//import net.dv8tion.jda.api.JDABuilder;
//import net.dv8tion.jda.api.entities.Activity;
//import net.dv8tion.jda.api.events.GenericEvent;
//import net.dv8tion.jda.api.events.ReadyEvent;
//
//import javax.security.auth.login.LoginException;
//import java.sql.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DiscordBot {
//    public static void main(String[] args) throws LoginException, InterruptedException, SQLException {
//
//        JDA bot = JDABuilder.createDefault("OTkyMDk5OTk4MjQwMDc5OTMy.GP023g.xQE1a9XPtDlBIOjgT6kSgTLGOZM4cV-Ns4_6mA")
//                .setActivity(Activity.playing("위키!작성 중..."))
//                .build();
//        bot.addEventListener(new HelloEvent());
//        bot.awaitReady();
//
//
//
//
//
//    }
//}
