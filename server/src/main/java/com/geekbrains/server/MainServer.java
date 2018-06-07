package com.geekbrains.server;

public class MainServer {

    // Домашнее задание
    // Разбор кода
    // Смена ника в БД по запросу /changenick newNick
    // Использовать запрос вида:
    // stmt.executeUpdate("UPDATE students SET score = 100 WHERE id = 4;");

    public static void main(String[] args) {
        new Server();
    }
}
