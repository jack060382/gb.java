package client;

import client.gui.ChatFrame;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatCommand {

    private final String commandRegExt = "#([a-z0-9_]+)#";
    private String commandName;
    private String commandText;
    private final String defaultCommand = "println";

    public ChatCommand(String incomeMessage) {
        if (incomeMessage.matches(commandRegExt)) {
            Pattern p = Pattern.compile(commandRegExt);
            Matcher m = p.matcher(incomeMessage);
            m.find();
            commandName = m.group(1);
            commandText = "";
        }
        else {
            commandName = defaultCommand;
            commandText = incomeMessage;
        }
    }

    public void execute(ChatFrame frame) {
        switch (commandName) {
            case defaultCommand : {
                frame.getTextArea().append(commandText+"\n");
            }
            case "authmodal" : {
                JDialog dialog = frame.createAuthDialog();
            }
        }

    }

}
