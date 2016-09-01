import HTMLCreator.*;
import amazon.*;
import jsoup.html.ASINListCreater;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ParserConfigurationException, SAXException, XPathExpressionException {
        Menu menu = new Menu();
        menu.menu();
        switch (menu.getVar()){
            case 1:
                ASINListCreater.fileWriter();
                menu.menu();
                break;
            case 2:
                Filter filter=new Filter();
                filter.setLookup();
                menu.menu();
                break;
            default:menu.menu();
        }
    }
}
