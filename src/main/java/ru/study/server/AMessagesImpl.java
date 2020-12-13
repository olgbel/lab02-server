package ru.study.server;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.faces.component.FacesComponent;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Singleton(name = "AMessages")
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class AMessagesImpl extends AMessages {

    @Override
    public String getMessage(String user, int index) throws InvalidParameterException {
        System.out.println("start AMessagesImpl");
        System.out.println("messages: " + messages);
        if (messages.get(user) == null || messages.get(user).size() - 1 < index)
            return "There is no such message.";

        return messages.get(user).get(index);
    }

    @Override
    @Lock(LockType.WRITE)
    public boolean addMessage(String user, final String message) {
        if (isEmptyOrNull(user) || isEmptyOrNull(message))
            return false;

        if (messages.get(user) == null)
            messages.put(user, new ArrayList<String>() {{
                add(message);
            }});
        else {
            ArrayList<String> userMessages = messages.get(user);
            userMessages.add(message);
            messages.put(user, userMessages);
        }
        return true;
    }

    @Override
    public List<String> getMessageList(String user) {
        return messages.get(user);
    }

    private boolean isEmptyOrNull(String s) {
        return s == null || s.trim().length() == 0;
    }
}
