package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.CommandHandlers.SlashCommandHandler;
import org.example.CommandHandlers.TextCommandHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Main {
    public static JDA jda;
    static final boolean updateCommands = false;
    static final boolean loadInfo = true;

    public static void main(String[] args) throws InterruptedException{
        jda = JDABuilder.createLight(Tokens.TOKEN, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new SlashCommandHandler(), new TextCommandHandler())
                .build().awaitReady();
        if(loadInfo){
            Thread loadInfoThread = new Thread(new LoadInfo());
            loadInfoThread.start();
        }

        if (updateCommands) {
            List<CommandData> commands = new ArrayList<>();
            commands.add(Commands.slash("hubert8ball", "Ask Hubert anything 🤔")
                    .addOption(OptionType.STRING, "question", "enter your question", true));
            commands.add(Commands.slash("hubertwisdom", "Get a wisdom from hubert 🤯"));
            commands.add(Commands.slash("help", "See what hubert can do! 😲"));
            jda.updateCommands().addCommands(commands).queue(x -> System.out.println("added " + x.size() + " commands"));

        }
    }

    public static BufferedImage createImage(String text, boolean isWisdom) throws IOException {
        int textLength = text.length();
        BufferedImage template;
        if(isWisdom){
            template = ImageIO.read(new File("assets/WisdomOfHubert.png"));
        }else{
            template = ImageIO.read(new File("assets/EightBall.png"));
        }
        Graphics2D g = template.createGraphics();
        g.setColor(Color.BLACK);

        if(textLength <= 22){
            g.scale(3.5,3.5);
            g.drawString(text, 20, 40);

        }else if(textLength <= 66){
            g.scale(3,3);
            List<String> lines = new ArrayList<>();
            String[] split = text.split("");
            String buffer = "";
            int bufferIndex = 0;
            for(int i = 0; i < textLength; i++){
                bufferIndex++;
                buffer += split[i];
                if(bufferIndex == 22){
                    lines.add(buffer);
                    buffer = "";
                    bufferIndex = 0;
                }
            }
            lines.add(buffer);

            for(int i = 0; i < lines.size(); i++){
                g.drawString( lines.get(i), 20, 30 + (i*12));
            }
        }else if(textLength <= 110){
            g.scale(3,3);
            List<String> lines = new ArrayList<>();
            String[] split = text.split("");
            String buffer = "";
            int bufferIndex = 0;
            for(int i = 0; i < textLength; i++){
                bufferIndex++;
                buffer += split[i];
                if(bufferIndex == 22){
                    lines.add(buffer);
                    buffer = "";
                    bufferIndex = 0;
                }
            }
            lines.add(buffer);

            for(int i = 0; i < lines.size(); i++){
                g.drawString( lines.get(i), 20, 20 + (i*12));
            }

        }else if(textLength <= 170){
            g.scale(2.5,2.5);
            List<String> lines = new ArrayList<>();
            String[] split = text.split("");
            String buffer = "";
            int bufferIndex = 0;
            for(int i = 0; i < textLength; i++){
                bufferIndex++;
                buffer += split[i];
                if(bufferIndex == 30){
                    lines.add(buffer);
                    buffer = "";
                    bufferIndex = 0;
                }
            }
            lines.add(buffer);

            for(int i = 0; i < lines.size(); i++){
                g.drawString( lines.get(i), 20, 20 + (i*12));
            }
        }
        g.dispose();
        return template;
    }
}