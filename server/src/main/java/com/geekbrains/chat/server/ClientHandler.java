package com.geekbrains.chat.server;

import org.apache.logging.log4j.Level;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickname;
    private static ExecutorService service = Executors.newFixedThreadPool(3);

    public String getNickname() {
        return nickname;
    }

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        service.execute(() -> {
            try {
                authServ(); // цикл аутентификации
                servTetATetClient(); // цикл общения с сервером (обмен текстовыми сообщениями и командами)
            } catch (IOException e) {
                server.getLogger().throwing(Level.ERROR,e);
            } finally {
                close();
            }
        });

//        new Thread(() -> {
//            try {
//                authServ(); // цикл аутентификации
//                servTetATetClient(); // цикл общения с сервером (обмен текстовыми сообщениями и командами)
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                close();
//            }
//        }).start();
    }

    private void servTetATetClient() throws IOException {
        while (true) {
            String msg = in.readUTF();
            server.getLogger().info("Сообщение от клиента: " + msg);
            if (msg.startsWith("/")) {
                if (msg.startsWith("/w ")) {
                    String[] tokens = msg.split(" ", 3); // /w user2 hello, user2
                    server.sendPrivateMsg(this, tokens[1], tokens[2]);
                    continue;
                }
                if (msg.startsWith("/change_nick ")) {
                    String[] tokens = msg.split(" ", 2); // /w user2 hello, user2
                    server.getAuthManager().changeNick(nickname, tokens[1]);
                    nickname = tokens[1];
                    sendMsg("/change_nick_OK " + nickname);
                    server.broadcastClientsList();

                }
                if (msg.equals("/end")) {
                    sendMsg("/end_confirm");
                    break;
                }
            } else {
                server.broadcastMsg(nickname + ": " + msg, true);
            }
        }
    }

    private void authServ() throws IOException {
        while (true) {
            String msg = in.readUTF();
            server.getLogger().info("Сообщение от клиента: " + msg);
            if (msg.startsWith("/auth ")) { // /auth login1 pass1
                String[] tokens = msg.split(" ", 3);
                String nickFromAuthManager = server.getAuthManager().getNicknameByLoginAndPassword(tokens[1], tokens[2]);
                if (nickFromAuthManager != null) {
                    if (server.isNickBusy(nickFromAuthManager)) {
                        sendMsg("Данный пользователь уже в чате");
                        continue;
                    }
                    nickname = nickFromAuthManager;
                    sendMsg("/authok " + nickname);
                    server.subscribe(this);
                    break;
                } else {
                    sendMsg("Указан неверный логин/пароль");
                }
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            server.getLogger().throwing(Level.ERROR,e);
        }
    }

    public void close() {
        server.unsubscribe(this);
        nickname = null;
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                server.getLogger().throwing(Level.ERROR,e);
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                server.getLogger().throwing(Level.ERROR,e);
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                server.getLogger().throwing(Level.ERROR,e);
            }
        }
    }
}
