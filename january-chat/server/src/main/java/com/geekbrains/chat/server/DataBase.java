package com.geekbrains.chat.server;

import java.sql.*;

public class DataBase implements AuthManager {
    public static Connection connection;
    public static PreparedStatement ps;
    public static PreparedStatement psUpData;
    public static Statement stmt;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:server/clients.db");
        stmt = connection.createStatement();
    }

    public static void prepareStatements() throws SQLException {
        ps = connection.prepareStatement("INSERT INTO clients (login, password, nickname) VALUES (?, ?, ?);");
        psUpData = connection.prepareStatement("UPDATE clients SET nickname = ? WHERE id = ?;");
    }
    public static void disconnect() {
        try{
            if(stmt!=null){
                stmt.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(ps!=null){
                ps.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(connection!=null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    DataBase(){
        try{
            connect();
            //dropAndCreateTableEx();
            prepareStatements();
            //fillTableEx();
            //readEx();
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public static void readEx() throws SQLException {
        //ResultSet - указатель на строчку из таблицы (грубо)
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM clients;")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " + rs.getInt(3));
            }
        }
    }

    public static void fillTableEx() throws SQLException {
        connection.setAutoCommit(false);
        for (int i = 1; i <= 3; i++) {
            ps.setString(1, "login" + i);
            ps.setString(2, "pass" + i);
            ps.setString(3, "user" + i);
            ps.executeUpdate();
        }
        connection.commit();
    }

    public static void dropAndCreateTableEx() throws SQLException {
        stmt.executeUpdate("DROP TABLE IF EXISTS clients;");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS clients (id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, password TEXT, nickname TEXT);");
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {

        try (ResultSet rs = stmt.executeQuery("SELECT * FROM clients;")) {
            while (rs.next()){
                if(rs.getString("login").equals(login) && rs.getString("password").equals(password))
                    return rs.getString("nickname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void changeNick(String oldnickname, String newnickname) {
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM clients;")) {
            while (rs.next()) {
                if(rs.getString("nickname").equals(oldnickname)){
                    psUpData.setString(1, newnickname);
                    psUpData.setInt(2,rs.getRow());
                    psUpData.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
