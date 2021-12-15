package server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);

    private ServerSocket serverSocket;
    private final List<ClientHandler> loggedUser;
    private final AuthService authService;
    private final ExecutorService service;

    public Server() {

        logger.info("Server started.");

        authService = new AuthService();
        loggedUser = new ArrayList<>();
        service = Executors.newCachedThreadPool();

        try {
            serverSocket = new ServerSocket(8888);
            logger.info("Server socket created on port 8888.");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            logger.fatal("IOException on server socket");
            e.printStackTrace();
        } finally {
            logger.info("Server shutdown.");
            service.shutdown();
        }
    }

    public ExecutorService getService() {return service;}

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void subscribe(ClientHandler user) {
        loggedUser.add(user);
    }

    public synchronized void unsubscribe(ClientHandler user) {
        loggedUser.remove(user);
    }

    public synchronized boolean isNotUserOccupied(String nick) {
        return !isUserOccupied(nick);
    }

    public synchronized boolean isUserOccupied(String nick) {
        /**
        for(ClineHandler user : loggedUser) {
            if (user.getName().equals(user)) {
                return true;
            }
        }
        return false;
        */

        /**
         loggedUser.stream()
             .filter(u -> u.getName().equals(u))
             .findFirst()
             .isPresent();
         */

        return loggedUser.stream()
                .anyMatch(u -> u.getNick().equals(nick));

    }

    public synchronized void privateMessage(String[] receivers, String outboundMessage) {
        for (ClientHandler user: loggedUser) {
            if (Arrays.asList(receivers).indexOf(user.getNick()) >= 0) {
                user.sendMessage(outboundMessage, true);
            }
        }
    }

    public synchronized void broadcastMessage(String outboundMessage) {
        /**
         for (ClientHandler user: loggedUser) {
            user.sendMessage(outboundMessage);
         }
         */

        /**
        loggedUser.forEach(new Consumer<ClientHandler>() {
            @Override
            public void accept(ClientHandler clientHandler) {
                clientHandler.sendMessage(outboundMessage);
            }
        });
         */
        //logger.info("User "+loggedUser.getNick()+" broadcasting message "+outboundMessage);
        loggedUser.forEach(clientHandler -> clientHandler.sendMessage(outboundMessage, true));
    }

}
