
import amazon.*;
import amazon.ItemLookup;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;


/**
 * Created by User on 24.08.2016.
 */
public class Main1 {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException, InterruptedException {

        XmlParserAmazon amazon = new XmlParserAmazon();
        Document doc = amazon.getDocument("C:\\xml.xml");
        System.out.println("Rank: "+ amazon.rank(doc));




    }
}
