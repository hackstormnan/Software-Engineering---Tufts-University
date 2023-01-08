package jrails;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JServer {
    public static void start(JRouter r) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/", new Handler(r));
            server.setExecutor(null);
            System.out.println("Starting server...point your web browser to http://localhost:8000");
	    System.out.flush();
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class Handler implements HttpHandler {
        JRouter r;

        Handler(JRouter r) {
            this.r = r;
        }

        public void handle(HttpExchange t) throws IOException {
            String response;

            System.out.println("Request: " + t.getRequestMethod() + " " + t.getRequestURI());

            // Add ?x=a&y=b&z=c etc from URI to params
            Map<String, String> params = new HashMap<String, String>();
            if (t.getRequestURI().getQuery() != null) {
                for (String q : t.getRequestURI().getQuery().split("&")) {
                    int i = q.indexOf("=");
                    params.put(URLDecoder.decode(q.substring(0, i), "UTF-8"),
                            URLDecoder.decode(q.substring(i + 1), "UTF-8")); // ignore UTF-8 etc decoding
                }
            }

            // Add form fields to params
            InputStream is = t.getRequestBody();
            String result = new BufferedReader(new InputStreamReader(is))
                    .lines().collect(Collectors.joining("\n"));
            if (result.indexOf("=") != -1) {
                for (String q : result.split("&")) {
                    int i = q.indexOf("=");
                    params.put(URLDecoder.decode(q.substring(0, i), "UTF-8"), URLDecoder.decode(q.substring(i + 1), "UTF-8"));
                }
            }

            try {
                response = r.route(t.getRequestMethod(), t.getRequestURI().getPath(), params).toString();
            } catch (UnsupportedOperationException e) {
                System.out.println("Routing caused unsupported operation exception.");
		System.out.flush();
                if (t.getRequestURI().getPath().equals("/test")) {
                    response = "<h1>Success!</h1><p>The server is running.</p>";
                } else if (t.getRequestURI().getPath().equals("/form")) {
                    // I used this to test parsing form parameters
                    response = "<form action=\"/create\" accept-charset=\"UTF-8\" method=\"post\"><div>Title<textarea name=\"title\">Old Title</textarea></div><div>Author<textarea name=\"author\">Old Author</textarea></div><div><input type=\"submit\" value=\"Save\"></div></form>";
                } else {
                    System.out.println("Params:");
                    for (String k : params.keySet()) {
                        System.out.println(k + " = " + params.get(k));
                    }
                    response = "Unsupported Operation Exception; see console for request details";
		    System.out.flush();
                }

            }
            t.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
