package com.geekbrains.chat.server;

//Наверное при старте программы создается сразу 3 потока и в дальнейшнем не тратится время и ресурсы на создание новых.
//чем newSingleThreadPull отличается от обычного new Thread()

public class MainApp {

    public static void main(String[] args) {
        new Server(8189);
    }
} 