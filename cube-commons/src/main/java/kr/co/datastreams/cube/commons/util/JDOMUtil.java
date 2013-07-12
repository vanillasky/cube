package kr.co.datastreams.cube.commons.util;

import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 1
 * Time: 오후 1:10
 * To change this template use File | Settings | File Templates.
 */
public class JDOMUtil {


    public static String asString(org.jdom2.Document doc) {
        XMLOutputter out = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        format.setExpandEmptyElements(true);

        out.setFormat(format);
        return out.outputString(doc);
    }

    public static org.jdom2.Document asDocument(String xml) throws IOException {
        org.jdom2.input.SAXBuilder  builder = new org.jdom2.input.SAXBuilder();
        org.jdom2.Document jdomDoc;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new StringReader(xml));
            jdomDoc = builder.build(reader);

        } catch (JDOMException e) {
            throw new IOException(e);
        } catch (IOException e) {
            throw e;
        } finally {
            reader.close();
        }

        return jdomDoc;
    }
}
