/**
 * Created by elliot on 05/07/2017.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpConnection {

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


    public static void main(String[] args) throws Exception{
        System.out.println("Starting");
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.start();
    }

    static class MyHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            System.out.println("Connection received!");
            System.out.println(httpExchange.getRemoteAddress());
            System.out.println("-----------------------------");
            String response = "Server Response...";


            OutputStream os = httpExchange.getResponseBody();
            InputStream is = httpExchange.getRequestBody();

            httpExchange.sendResponseHeaders(200, response.getBytes().length);

            os.write(response.getBytes("UTF-8"));
            os.close();


            // Now string has been received delegate message to appropriate class.
            // Parse the request.
            ReqParse reqParse = new ReqParse(convertStreamToString(is));
            Request newReg = reqParse.parse();






        }
    }
}
