package mj;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

/**
 * @author: liyangjin
 * @create: 2024-01-02 21:09
 * @Description:
 */
public class MessageListener extends ListenerAdapter {
    public static void main(String[] args) {
        JDA jda = JDABuilder.createDefault("MTEyMDUzMzk1NzgzOTE3MTY2Nw.GMMeqc.pbJeE1bHBjaGpHkz3XH3VKkNGzsvg87fZ6SC1I")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
        jda.addEventListener(new MessageListener());
        System.out.println("success");
    }

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        System.out.println("onMessageUpdate messageId = "+event.getMessageId());
        if (event.getMessage().getAttachments() != null && event.getMessage().getAttachments().size() > 0) {
            Message.Attachment attachment = event.getMessage().getAttachments().get(0);
            System.out.println(String.format("filename=%s content_type=%s proxy_url=%s url=%s", attachment.getFileName(), attachment.getContentType(),
                    attachment.getProxyUrl(), attachment.getUrl()));
        }
        if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getChannelType().name(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("onMessageReceived messageId = "+event.getMessageId());
        if (event.getMessage().getAttachments() != null && event.getMessage().getAttachments().size() > 0) {
            Message.Attachment attachment = event.getMessage().getAttachments().get(0);
            System.out.println(String.format("id=%s filename=%s content_type=%s url=%s", attachment.getId(), attachment.getFileName(), attachment.getContentType(),
                    attachment.getUrl()));
        }
        if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getChannelType().name(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }
    }

}


