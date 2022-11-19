package org.example.CommandHandlers;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;
import org.example.Main;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static org.example.LoadInfo.eightBalls;
import static org.example.LoadInfo.wisdoms;

public class TextCommandHandler extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e){
        String name = e.getMessage().getContentRaw();
        String[] args = name.split(" ");
        switch (args[0]){
            case ".hubert8ball":
                if(args.length < 2){
                    e.getMessage().reply("Ask me something!🤔").mentionRepliedUser(false).queue();
                }
                String eightBallText = eightBalls.get((int) Math.floor(Math.random() * eightBalls.size()));
                try {
                    ImageIO.write(Main.createImage(eightBallText, false), "PNG", new File("eightball.png"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                String question = "";
                for(int i = 1; i < args.length; i++){
                    question += args[1] + " ";
                }
                EmbedBuilder eightBallBuilder;
                eightBallBuilder = new EmbedBuilder()
                        .setTitle(question.replace("/?/", "") + "?")
                        .setImage("attachment://eightball.png");
                e.getMessage().replyEmbeds(eightBallBuilder.build())
                        .addFiles(FileUpload.fromData(new File("eightball.png")))
                        .mentionRepliedUser(false)
                        .queue();
                break;
            case ".hubertwisdom":
                String wisdomText = wisdoms.get((int) Math.floor(Math.random() * wisdoms.size()));
                try {
                    ImageIO.write(Main.createImage(wisdomText, false), "PNG", new File("wisdom.png"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                EmbedBuilder wisdomBuilder = new EmbedBuilder()
                        .setImage("attachment://wisdom.png");
                e.getMessage().replyEmbeds(wisdomBuilder.build())
                        .addFiles(FileUpload.fromData(new File("wisdom.png")))
                        .mentionRepliedUser(false).queue();
                break;
            case ".hubert": case "help":
                EmbedBuilder help = new EmbedBuilder()
                        .setTitle("I know all 🥳🙌")
                        .setDescription("``` /hubertwisdom --> get a wisdom from hubert!🤯 \n" +
                                " /hubert8ball --> ask hubert anything!🤔💭 \n " +
                                "\n\n" +
                                "Text version: \n\n" +
                                ".hubertwisdom --> get a wisdom from hubert!🤯 \n" +
                                ".hubert8ball --> ask hubert anything!🤔💭```");
                e.getMessage().replyEmbeds(help.build()).mentionRepliedUser(false).queue();

        }
    }
}
