package ru.study.server;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Абстрактный класс для хранения коллекций сообщений пользователей.
 *
 * @author Ю.Д.Заковряшин, 2019-2020
 */
@Remote
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AMessages implements Serializable {

    /**
     * Коллекция сообщений типа Map, в которой ключ представлен логином
     * пользователя, а значение - списком сообщений.
     */
    protected Map<String, MessageList> messages;

    /**
     * Конструктор по умолчанию.
     */
    public AMessages() {
        this.messages = new HashMap<>();
    }

    /**
     * Метод возвращает сообщение заданного пользователя по индексу сообщения
     * (порядковому номеру).
     *
     * @param user  логин пользователя.
     * @param index индекс сообщения
     * @return сообщение пользователя или null, если такого пользователя нет или
     * у него нет сообщения с данным индексом.
     */
    public abstract String getMessage(String user, int index) throws InvalidParameterException;

    /**
     * Метод добавляет новое сообщение к коллекции заданного пользователя. Если
     * пользователя с указанным логином нет в коллекции, то создаётся новый
     * элемент коллекции с ключом с заданным параметром login.
     *
     * @param user    логин пользователя.
     * @param message сообщение пользователя.
     * @return значение true в случае, если сообщение успешно добавлено, false -
     * в случае, когда логин пользователя или сообщение являются недопустимыми.
     */
    public abstract boolean addMessage(String user, String message);

    /**
     * Метод возвращает коллекци сообщений данного пользователя.
     *
     * @param user логин пользователя.
     * @return коллекция сообщений пользователя.
     */
    public abstract MessageList getMessageList(String user);
}

