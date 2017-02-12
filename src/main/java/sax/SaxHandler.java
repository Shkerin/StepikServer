package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import reflection.ReflectionHelper;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
@SuppressWarnings("UnusedDeclaration")
public class SaxHandler extends DefaultHandler {

    private static final String CLASSNAME = "class";
    private String element = null;
    private Object object = null;
    private boolean enableLog = false;

    public void startDocument() throws SAXException {
        if (enableLog) {
            System.out.println("Start document");
        }
    }

    public void endDocument() throws SAXException {
        if (enableLog) {
            System.out.println("End document ");
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (!qName.equals(CLASSNAME)) {
            element = qName;
        } else {
            String className = attributes.getValue(0);
            if (enableLog) {
                System.out.println("Class name: " + className);
            }
            object = ReflectionHelper.createInstance(className);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = null;
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if (element != null) {
            String value = new String(ch, start, length);
            if (enableLog) {
                System.out.println(element + " = " + value);
            }
            ReflectionHelper.setFieldValue(object, element, value);
        }
    }

    public Object getObject() {
        return object;
    }

}
