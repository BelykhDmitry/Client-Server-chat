package com.geekbrains.server;

import java.sql.*;

public class SQLHandler {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPassword(String login, String password) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname FROM users WHERE login = '" + login + "' AND password = '" + password + "';");
            if(rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean tryToRegister(String login, String password, String nickname) {
        try {
            stmt.executeUpdate(String.format("INSERT INTO users (login, password, nickname) VALUES ('%s', '%s', '%s');", login, password, nickname));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeNick(String nickname, String oldNickname) throws SQLException {
        stmt.executeUpdate("UPDATE users SET nickname = '" + nickname + "' WHERE nickname ='" + oldNickname + "';");
    }
}
