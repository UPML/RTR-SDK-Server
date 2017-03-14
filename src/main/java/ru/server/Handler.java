package ru.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;

public class Handler implements HttpHandler {

    private static final Logger logger = LoggerFactory.getLogger(Handler.class);
    private XmlFile xmlFile;

    Handler(XmlFile xmlFile) {
        this.xmlFile = xmlFile;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        /*
            we should work with a database here
        */

        logger.info(xmlFile.getXmlFile());
        byte[] bytes = xmlFile.getXmlFile().getBytes();
        httpExchange.sendResponseHeaders(Authentication.OK, bytes.length);

        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(bytes);
        outputStream.close();
    }

}

