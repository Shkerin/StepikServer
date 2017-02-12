package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Class to create object from xml file
 *
 * @author Vladimir Shkerin
 * @since 12.02.2017
 */
public class ReadXMLFileSAX {

    public static Object readXML(String xmlFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            SaxHandler handler = new SaxHandler();
            parser.parse(xmlFile, handler);

            return handler.getObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
