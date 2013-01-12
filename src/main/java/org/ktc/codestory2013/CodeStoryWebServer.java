package org.ktc.codestory2013;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

@SuppressWarnings("restriction")
public class CodeStoryWebServer implements HttpHandler {

    public CodeStoryWebServer() {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String body = "";

        if ("/update".equals(exchange.getRequestURI().getPath())) {
            body = "kiffe";
        } else {
            body = "hello";
        }

        byte[] response = body.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
        exchange.getResponseBody().write(response);
        exchange.close();
    }

    public static void main(String[] args) throws Exception {
        int port = getServerPort();
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new CodeStoryWebServer());
        server.start();
    }

    private static int getServerPort() {
        String portOnHeroku = System.getenv("PORT");
        if (portOnHeroku == null) {
            portOnHeroku = "5000";
        }
        return Integer.parseInt(portOnHeroku);
    }

}