package ru.study.server;

import javax.ejb.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Singleton(name = "AMessages")
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class AMessagesImpl extends AMessages {

    @Override
    public String getMessage(String user, int index) throws InvalidParameterException {
        if (messages.get(user) == null || messages.get(user).getMessages().size() - 1 < index)
            return "There is no such message.";

        return messages.get(user).getMessages().get(index);
    }

    @Override
    @Lock(LockType.WRITE)
    public boolean addMessage(String user, final String message) {
        if (isEmptyOrNull(user) || isEmptyOrNull(message))
            return false;

        if (messages.get(user) == null) {
            MessageList messageList = new MessageList();
            messageList.setMessages(new ArrayList<String>() {{
                add(message);
            }});
            messages.put(user, messageList);
        } else {
            List<String> userMessages = messages.get(user).getMessages();
            userMessages.add(message);
            MessageList messageList = new MessageList();
            messageList.setMessages(userMessages);
            messages.put(user, messageList);
        }
        return true;
    }

    @Override
    public MessageList getMessageList(String user) {
        return messages.get(user);
    }

    private boolean isEmptyOrNull(String s) {
        return s == null || s.trim().length() == 0;
    }
}
