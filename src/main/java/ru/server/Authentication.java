package ru.server;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpPrincipal;
import com.sun.tools.javac.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.String;

public class Authentication extends Authenticator {

    private static final Logger logger = LoggerFactory.getLogger(Authentication.class);
    private static final String TEST = "test";
    public static final Integer OK = 200;
    private final Integer INCORRECT_HEADER = 401;
    private final Integer INCORRECT_XML_FILE = 501;
    private XmlFile xmlFile;

    Authentication (XmlFile xmlFile) {
        this.xmlFile = xmlFile;
    }

    private StringBuffer getXmlFile(InputStream inputStream) throws IOException {
        StringBuffer xmlFile = new StringBuffer();
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            xmlFile.append(inputLine);
        }
        return xmlFile;
    }

    private Pair<Boolean, Integer> isCorrectMethod (String method) {
        logger.info(method);
        if (method.equals("POST")) {
            return Pair.of(true, OK);
        } else {
            return Pair.of(false, INCORRECT_HEADER);
        }
    }

    private Pair<Boolean, Integer> isCorrectXmlFile (String xmlFile) {
        logger.info(xmlFile);
        /*
            stub
        */
        return Pair.of(true, OK);
    }

    @Override
    public Result authenticate(HttpExchange httpExchange) {
        logger.info("somebody try connect");
        Pair<Boolean, Integer> correctMethod = isCorrectMethod(httpExchange.getRequestMethod());
        if (correctMethod.fst.equals(false)) {
            return new Failure(correctMethod.snd);
        }
        try {
            xmlFile.setXmlFile(getXmlFile(httpExchange.getRequestBody()).toString());
            isCorrectXmlFile(xmlFile.getXmlFile());
            return new Success(new HttpPrincipal(TEST, TEST));
        } catch (IOException error) {
            logger.info(error.toString());
            return new Failure(INCORRECT_XML_FILE);
        }
    }
}