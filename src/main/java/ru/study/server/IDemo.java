package ru.study.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * �?нтерфейс определяет методы для работы с коллекциями сообщений пользователей.
 *
 * @author Ю.Д.Заковряшин, 2019-2020
 */
@WebService
public interface IDemo {

    /**
     * Метод позволяет добавить к коллекции заданного пользователя новое
     * сообщение.
     *
     * @param user    логин пользователя.
     * @param message добавляемое сообщение.
     * @return значение true в случае, если сообщение успешно добавлено, false -
     * случае, когда логин пользователя или сообщение равны null или пусты.
     */
    @WebMethod
    boolean add(@WebParam(name = "user") String user, @WebParam(name = "message") String message);

    /**
     * Метод позволяет получить сообщение заданного пользователя по его индексу
     * (порядковому номеру). Порядковый номер присваивается сообщению
     * автоматически при его помещении в коллекцию сообщений пользователя. Для
     * каждого пользователя ведётся отдельная нумерация сообщений.
     *
     * @param user  логин пользователя.
     * @param index индекс (порядковый номер).
     * @return сообщение данного пользователя с индексом index или null, если
     * такого пользователя нет или у заданного пользователя нет сообщения с
     * таким индексом.
     */
    @WebMethod
    String getMessage(@WebParam(name = "user") String user, @WebParam(name = "index") int index);

    /**
     * Метод позволяет получить коллекцию всех сообщений заданного пользователя.
     *
     * @param user логин пользователя.
     * @return коллекция сообщений заданного пользователя.
     */
    @WebMethod
    MessageList getAllMessage(@WebParam(name = "user") String user);
}

