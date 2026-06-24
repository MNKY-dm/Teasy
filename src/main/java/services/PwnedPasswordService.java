package services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

public final class PwnedPasswordService {

    private static final String API_URL = "https://api.pwnedpasswords.com/range/";
    private static final Duration CONNECT_TIMEOUT = Duration.ofSeconds(1);
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(1);

    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(CONNECT_TIMEOUT)
            .build();

    private PwnedPasswordService() {
    }

    public static boolean isSafe(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }

        String sha1 = sha1Hex(password);
        String prefix = sha1.substring(0, 5);
        String suffix = sha1.substring(5);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + prefix))
                .timeout(REQUEST_TIMEOUT)
                .header("User-Agent", "Teasy-Password-Check")
                .GET()
                .build();

        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return true;
            }

            String body = response.body();
            String[] lines = body.split("\\R");

            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length < 2) {
                    continue;
                }

                String returnedSuffix = parts[0].trim();
                if (returnedSuffix.equalsIgnoreCase(suffix)) {
                    return false;
                }
            }

            return true;

        } catch (HttpConnectTimeoutException e) {
            return true;
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            return true;
        }
    }

    private static String sha1Hex(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02X", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-1 indisponible", e);
        }
    }
}
