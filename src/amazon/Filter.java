package amazon;

import HTMLCreator.CreateWebPage;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by User on 27.08.2016.
 */
public class Filter {
    ItemLookup lookup = new ItemLookup();
    XmlParserAmazon amazon = new XmlParserAmazon();
    CreateWebPage toList = new CreateWebPage();



    public void setLookup() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, InterruptedException {
        FileInputStream fstream = new FileInputStream("ASINs.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        CreateWebPage page = new CreateWebPage();

        while ((strLine = br.readLine()) != null) {
            boolean isWeight=true;
            boolean isRank=true;
            boolean isPrise=true;
            System.out.println(strLine);
           // Document doc = amazon.getDocument(lookup.itemAttributes(strLine));
           // Thread.sleep(1000);
            Document doc2 = amazon.getDocument(lookup.itemGetterPrise(strLine));
            Thread.sleep(1000);
            Double height = amazon.packageAndItemDimensions(doc2, "package", "Height");
            Double length = amazon.packageAndItemDimensions(doc2, "package", "Length");
            Double width = amazon.packageAndItemDimensions(doc2, "package", "Width");
            Double weight = amazon.packageAndItemDimensions(doc2, "package", "Weight");
            int amount=amazon.itemAmount(doc2);
            int rank = amazon.rank(doc2);
            String price = amazon.itemPrice(doc2, "FormattedPrice");
            String imageUrl = amazon.mediumImage(doc2);
            String asin = amazon.itemASINandSalesRank(doc2, "ASIN");
            String title = amazon.itemTitle(doc2, "Title");
            String itemLink = amazon.itemLink(doc2);
            String productGroup=amazon.productGroup(doc2);


            if(rank>6000)isRank=false;
            if(weight>2000)isWeight=false;
            if(amount>3000)isPrise=false;
//
//                        System.out.println(rank);
//                        System.out.println("Weight:"+weight);
//                        System.out.println("Width:"+width);
//                        System.out.println("Length:"+length);
//                        System.out.println("Height:"+height);
//                        System.out.println(imageUrl);
//                        System.out.println(price);
            System.out.println("Product Group is " + productGroup);
            System.out.println("######################################################");
            Thread.sleep(1000);

            if (isWeight&&isRank&&isPrise) {
                page.setItemLink(itemLink);
                page.setHeight(height.toString());
                page.setLength(length.toString());
                page.setWeight(weight.toString());
                page.setWidth(width.toString());
                page.setImage(imageUrl);
                page.setPrice(price);
                page.setRank(rank+"");
                page.setAsin(asin);
                page.setTitle(title);
                page.setProductGroup(productGroup);
                page.pageCrater();
            }

        }
        br.close();
    }


}
