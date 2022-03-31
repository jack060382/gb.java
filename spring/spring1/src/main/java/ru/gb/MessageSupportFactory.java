package ru.gb;

import java.io.IOException;
import java.util.Properties;

public class MessageSupportFactory {
    private static MessageSupportFactory instance;

    private MessageRender messageRender;
    private MessageProvider messageProvider;
    private Properties properties;

    static {
        instance = new MessageSupportFactory();
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageRender getMessageRender() {
        return messageRender;
    }

    public MessageProvider getMessageProvider() {
        return messageProvider;
    }

    private MessageSupportFactory() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/message-context.properties"));
            String renderClass = properties.getProperty("render.class");
            String providerClass = properties.getProperty("provider.class");

            messageRender = (MessageRender) Class.forName(renderClass).newInstance();
            messageProvider = (MessageProvider) Class.forName(providerClass).newInstance();

            messageRender.setMessageProvider(messageProvider);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
