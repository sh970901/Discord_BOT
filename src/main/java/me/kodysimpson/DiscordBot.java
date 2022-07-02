package me.kodysimpson;


import me.kodysimpson.events.HelloEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;

import javax.security.auth.login.LoginException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscordBot {
    public static void main(String[] args) throws LoginException, InterruptedException, SQLException {

        JDA bot = JDABuilder.createDefault("Token")
                .setActivity(Activity.playing("making...."))
                .build();
        bot.addEventListener(new HelloEvent());
        bot.awaitReady();




    }

    public void onEvent(GenericEvent event)
    {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }
}
