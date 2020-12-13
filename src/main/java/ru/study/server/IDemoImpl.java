package ru.study.server;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService(serviceName = "IDemoService", name = "IDemo")
@Remote(IDemo.class)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class IDemoImpl implements IDemo {

    public IDemoImpl() {
    }

    @EJB(name = "AMessages")
    private final AMessages aMessages = new AMessagesImpl();

    @WebMethod
    @Override
    public boolean add(String user, final String message) {
        return aMessages.addMessage(user, message);
    }

    @WebMethod
    @Override
    public String getMessage(String user, int index) {
        System.out.println("start IDemoImpl with user: " + user + "; index: " + index);
        return aMessages.getMessage(user, index);
    }

    @WebMethod
    @Override
    public MessageList getAllMessage(String user) {
        MessageList messageList = new MessageList();
        messageList.setMessages(aMessages.getMessageList(user).getMessages());
        return messageList;
    }
}