package Websocket;


import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collections;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;


public class ChatServer extends WebSocketServer {
    public ChatServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }


    public ChatServer(InetSocketAddress address) {
        super(address);
    }

    public ChatServer(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(
                conn.getRemoteSocketAddress().getAddress().getHostAddress() + " has connected!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println(conn + " has disconnected!");
    }

    @Override
    public void onMessage(WebSocket conn, String message){
        System.out.println(conn + "Message: "+ message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            System.out.println("onError Method");
            System.out.println(conn + "Message: "+ ex);
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }
}

