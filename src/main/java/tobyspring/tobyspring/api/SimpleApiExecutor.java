package tobyspring.tobyspring.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor {

    @Override
    public String execute(URI uri) throws IOException {
        String response;
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            response = bf.lines().collect(Collectors.joining());
        }
        return response;
    }
}
