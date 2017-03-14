
import org.apache.log4j.spi.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.logging.Logger;

public class XmlParser {

    //private static Logger log = log.getLogger(XmlParser.class);

    private static final String TAG_TOTAL = "total";
    private static final String TAG_NORMALIZED_VALUE = "normalizedValue";

    public ContextCheck xmlReader(String xmlString) throws Exception {

        ContextCheck check = new ContextCheck();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlString)));

            Element root = document.getDocumentElement();
            NodeList nodes = root.getChildNodes().item(1).getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (TAG_TOTAL.equals(node.getNodeName())) {
                    parseTotal(check, node);
                }
            }

            return check;

        } catch (Exception error) {
            error.printStackTrace();
            throw error;
        }

    }

    private void parseTotal(ContextCheck check, Node total) {
        //log.info("parse totalSum\n");
        NodeList nodes = total.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (TAG_NORMALIZED_VALUE.equals(node.getNodeName())) {
                String value = node.getTextContent();
                check.setTotalSum(Double.valueOf(value));
            }
        }
    }

}
