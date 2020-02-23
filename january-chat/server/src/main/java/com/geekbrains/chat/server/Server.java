package com.geekbrains.chat.server;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private AuthManager authManager;
    private List<ClientHandler> clients;
    private final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Logger logger = LogManager.getLogger(Server.class);

    public AuthManager getAuthManager() {
        return authManager;
    }
    public Logger getLogger(){return logger;}

    public Server(int port) {
        clients = new ArrayList<>();
        //authManager = new BasicAuthManager();
        authManager = new DataBase(this);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Сервер запущен. Ожидаем подключения клиентов...");
            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            logger.throwing(Level.ERROR,e);
        } finally {
            DataBase.disconnect();
            logger.info("Сервер завершил работу");
        }

    }

    public void broadcastMsg(String msg, boolean withDateTime) {
        if (withDateTime) {
            msg = String.format("[%s] %s", LocalDateTime.now().format(DTF), msg);
        }
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public void broadcastClientsList() {
        StringBuilder stringBuilder = new StringBuilder("/clients_list ");
        for (ClientHandler o : clients) {
            stringBuilder.append(o.getNickname()).append(" ");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        String out = stringBuilder.toString();
        broadcastMsg(out, false);
    }

    public void sendPrivateMsg(ClientHandler sender, String receiverNickname, String msg) {
        if (sender.getNickname().equals(receiverNickname)) {
            sender.sendMsg("Нельзя посылать личное сообщение самому себе");
            return;
        }
        for (ClientHandler o : clients) {
            if (o.getNickname().equals(receiverNickname)) {
                o.sendMsg("from " + sender.getNickname() + ": " + msg);
                sender.sendMsg("to " + receiverNickname + ": " + msg);
                return;
            }
        }
        sender.sendMsg(receiverNickname + " не в сети");
    }

    public boolean isNickBusy(String nickname) {
        for (ClientHandler o : clients) {
            if (o.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        broadcastMsg(clientHandler.getNickname() + " зашел в чат", false);
        clients.add(clientHandler);
        broadcastClientsList();
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        if (!clientHandler.getNickname().equals("null"))
            broadcastMsg(clientHandler.getNickname() + " вышел из чата", false);
        broadcastClientsList();
    }
}


