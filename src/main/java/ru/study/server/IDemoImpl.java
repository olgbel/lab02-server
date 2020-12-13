package ru.study.server;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(serviceName = "IDemoService", name = "IDemo")
@Remote(IDemo.class)
@SOAPBinding(style = SOAPBinding.Style.RPC)
@Singleton
public class IDemoImpl implements IDemo {

    public IDemoImpl() {
    }

    @EJB(name = "AMessages")
    private AMessages aMessages;

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

//    @Override
//    public List<String> getAllMessage(String user) {
//        return aMessages.getMessageList(user);
//    }
}