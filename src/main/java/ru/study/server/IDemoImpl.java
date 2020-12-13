package ru.study.server;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

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
        return aMessages.getMessage(user, index);
    }

    @WebMethod
    @Override
    public MessageList getAllMessage(String user) {
        return new MessageList(aMessages.getMessageList(user).getMessages());
    }
}