package org.example.CommandHandlers;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;
import org.example.Main;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import static org.example.LoadInfo.eightBalls;
import static org.example.LoadInfo.wisdoms;

public class SlashCommandHandler extends ListenerAdapter {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        String name = e.getName();
        switch (name) {
            case "hubert8ball":
                String eightBallText = eightBalls.get((int) Math.floor(Math.random() * eightBalls.size()));
                try {
                    ImageIO.write(Main.createImage(eightBallText, false), "PNG", new File("eightball.png"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                EmbedBuilder eightBallBuilder;
                    eightBallBuilder = new EmbedBuilder()
                            .setTitle(e.getOption("question").getAsString().replace("/?/", "") + "?")
                            .setImage("attachment://eightball.png");
                e.replyEmbeds(eightBallBuilder.build())
                        .addFiles(FileUpload.fromData(new File("eightball.png")))
                        .queue();
                break;

            case "hubertwisdom":
                String wisdomText = wisdoms.get((int) Math.floor(Math.random() * wisdoms.size()));
                try {
                    ImageIO.write(Main.createImage(wisdomText, false), "PNG", new File("wisdom.png"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                EmbedBuilder wisdomBuilder = new EmbedBuilder()
                        .setImage("attachment://wisdom.png");
                e.replyEmbeds(wisdomBuilder.build())
                        .addFiles(FileUpload.fromData(new File("wisdom.png"))).queue();
                break;

            case "help":
                EmbedBuilder help = new EmbedBuilder()
                        .setTitle("I know all 🥳🙌")
                        .setDescription("``` /hubertwisdom --> get a wisdom from hubert!🤯 \n" +
                                " /hubert8ball --> ask hubert anything!🤔💭 \n " +
                                "\n\n" +
                                "Text version: \n\n" +
                                ".hubertwisdom --> get a wisdom from hubert!🤯 \n" +
                                ".hubert8ball --> ask hubert anything!🤔💭```");
                e.replyEmbeds(help.build()).queue();
        }
    }
}
