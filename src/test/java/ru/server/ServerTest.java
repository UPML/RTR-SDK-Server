package ru.server;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerTest {

    private static final Logger logger = LoggerFactory.getLogger(ServerTest.class);
    private static final String IP = "192.168.1.238";
    private static final String PORT = "1337";

    /*

    If the test has annotation ignore, this could mean that test can be incorrect
    after integration with another part of project

    */

    @Test
    @Ignore
    public void testServerMethodPOST() throws Exception {
        URL url = new URL("http://" + IP + ":" + PORT + "/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);
    }

    @Test
    @Ignore
    public void testServerMethodGET() throws Exception {
        URL url = new URL("http://" + IP + ":" + PORT + "/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        assertEquals(401, responseCode);
    }

    @Test
    @Ignore
    public void testServerRequestData() throws Exception {
        URL url = new URL("http://" + IP + ":" + PORT + "/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        String data = "Just a one sentense";
        byte[] bytes = data.getBytes("UTF-8");
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.getOutputStream().write(bytes);

        InputStream inputStream;
        int responseCode = connection.getResponseCode();

        assertEquals(200, responseCode);

        inputStream = connection.getInputStream();

        StringBuilder xmlFile = new StringBuilder();
        String inputLine;

        BufferedReader input = new BufferedReader(new InputStreamReader(
                inputStream));
        while ((inputLine = input.readLine()) != null) {
            xmlFile.append(inputLine);
        }
        assertEquals(data, xmlFile.toString());
    }
}
