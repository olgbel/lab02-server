package ru.study.server;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "messages")
public class MessageList {
    private List<String> messages;

    public MessageList() {

    }

    public MessageList(List<String> messages) {
        this.messages = messages;
    }

    @XmlElement(name = "message")
    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
