package amazon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.net.URL;


public class XmlParserAmazon {


    public Document getDocument(String url) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new URL(url).openStream());
        //Document doc = dBuilder.parse(url);
        doc.getDocumentElement().normalize();
        return doc;
    }

    public double packageAndItemDimensions(Document doc, String dimensions, String request) throws XPathExpressionException {

        XPath xPath = XPathFactory.newInstance().newXPath();
        if (dimensions.equals("item")) {
            dimensions = "/ItemLookupResponse/Items/Item/ItemAttributes/ItemDimensions";
        }
        if (dimensions.equals("package")) {
            dimensions = "/ItemLookupResponse/Items/Item/ItemAttributes/PackageDimensions";
        }

        double respond = 0;
        NodeList nodePackageDimensions = (NodeList) xPath.compile(dimensions).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodePackageDimensions.getLength(); i++) {
            NodeList nNode = (NodeList) nodePackageDimensions.item(i);
            for (int j = 0; j < nNode.getLength(); j++) {
                Node nnNode = nNode.item(j);
                String nodeName = nnNode.getNodeName();
                String content = nnNode.getTextContent();
                Element eElement = (Element) nnNode;
                String units = eElement.getAttribute("Units");
                if (nodeName.equals(request))
                    respond = Dimensions(nodeName, content, eElement, units, request);
            }
        }
        return respond;
    }

    private double Dimensions(String nodeName, String content, Element eElement, String units, String request) {
        double height;
        double length;
        double weight;
        double width;
        double respond = 0;
        double hundredthsInchesToCm = 2.4 / 100;

        if (nodeName.equals("Height") && request.equals("Height")) {
            height = Double.parseDouble(content);
            if (eElement.getAttribute("Units").equals("hundredths-inches")) {
                respond = height * 2.54 / 100;
            } else respond = height * 2.4;
        }

        if (nodeName.equals("Length") && request.equals("Length")) {
            length = Double.parseDouble(content);
            if (eElement.getAttribute("Units").equals("hundredths-inches")) {
                respond = length * 2.54 / 100;
            } else respond = length * 2.4;
        }
        if (nodeName.equals("Width") && request.equals("Width")) {
            width = Double.parseDouble(content);
            if (eElement.getAttribute("Units").equals("hundredths-inches")) {
                respond = width * 2.54 / 100;
            } else respond = width * 2.4;
        }
        if (nodeName.equals("Weight") && request.equals("Weight")) {
            weight = Double.parseDouble(content);
            if (eElement.getAttribute("Units").equals("hundredths-pounds")) {
                respond = weight * 453.59237 / 100;
            } else respond = weight * 0.45359237;
        }
        return respond;
    }

    public String itemAttributes(Document doc, String request) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String itemAttributes = "/ItemSearchResponse/Items/Item/ItemAttributes";
        String respond = "";
        String content;
        NodeList nodeItemAttributes = (NodeList) xPath.compile(itemAttributes).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeItemAttributes.getLength(); i++) {
            Node nNode = nodeItemAttributes.item(i);
            NodeList nnodeItemAttributes = (NodeList) nNode;
            for (int j = 0; j < nnodeItemAttributes.getLength(); j++) {
                Node nnNode = nnodeItemAttributes.item(j);
                if (nnNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nnNode;
                    String nameOfNode = nnNode.getNodeName();
                    if (nameOfNode.equals(request)) {
                        content = eElement.getTextContent();
                        respond = respond + " " + content;
                    }
                }
            }

        }
        return respond;
    }

    private String moreSearchResults(Document doc, String request) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String items = "/ItemSearchResponse/Items";
        String respond = null;
        NodeList nodeList = (NodeList) xPath.compile(items).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (request.equals("TotalResults")) {
                    String totalResults = eElement.getElementsByTagName("TotalResults").item(0).getTextContent();
                    respond = totalResults;
                }
                if (request.equals("TotalPages")) {
                    String totalPages = eElement.getElementsByTagName("TotalPages").item(0).getTextContent();
                    respond = totalPages;
                }
                if (request.equals("MoreSearchResultsUrl")) {
                    String moreSearchResultsUrl = eElement.getElementsByTagName("MoreSearchResultsUrl").item(0).getTextContent();
                    respond = moreSearchResultsUrl;
                }
            }
        }
        return respond;
    }

    private void nodeRequest(Document doc) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String request = "/ItemSearchResponse/Items/Request";
        NodeList nodeRequest = (NodeList) xPath.compile(request).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeRequest.getLength(); i++) {
            Node nNode = nodeRequest.item(i);
            //System.out.println("Current Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String isValid = eElement.getElementsByTagName("IsValid").item(0).getTextContent();
                //      System.out.println("IsValid: " + isValid);
                //System.out.println("TotalPages : " + eElement.getElementsByTagName("TotalPages").item(0).getTextContent());
            }
        }
    }

    public String itemASINandSalesRank(Document doc, String request) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String item = "/ItemLookupResponse/Items/Item";
        String respond = null;
        NodeList nodeItem = (NodeList) xPath.compile(item).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeItem.getLength(); i++) {
            Node nNode = nodeItem.item(i);
            //System.out.println("Current Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (request.equals("ASIN")) {
                    String asin = eElement.getElementsByTagName("ASIN").item(0).getTextContent();
                    // System.out.println(asin);
                    respond = asin;
                }
            }
        }
        return respond;
    }

    public String mediumImage(Document doc) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String mediumImage = "/ItemLookupResponse/Items/Item/MediumImage";
        NodeList nodeMediumImage = (NodeList) xPath.compile(mediumImage).evaluate(doc, XPathConstants.NODESET);
        String respond = null;
        for (int i = 0; i < nodeMediumImage.getLength(); i++) {
            Node nNode = nodeMediumImage.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                respond = eElement.getElementsByTagName("URL").item(0).getTextContent();
            }
        }
        return respond;
    }

    public String itemPrice(Document doc, String request) throws XPathExpressionException {

        XPath xPath = XPathFactory.newInstance().newXPath();
        String mediumImage = "/ItemLookupResponse/Items/Item/Offers/Offer/OfferListing/Price";
        NodeList nodeMediumImage = (NodeList) xPath.compile(mediumImage).evaluate(doc, XPathConstants.NODESET);
        String respond = null;
        for (int i = 0; i < nodeMediumImage.getLength(); i++) {
            Node nNode = nodeMediumImage.item(i);
            // System.out.println(nNode);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                respond = eElement.getElementsByTagName("FormattedPrice").item(0).getTextContent();
            }
        }
        return respond;
//        XPath xPath = XPathFactory.newInstance().newXPath();
//        String items =
//        String respond = null;
//        NodeList nodeList = (NodeList) xPath.compile(items).evaluate(doc, XPathConstants.NODESET);
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node nNode = nodeList.item(i);
//            System.out.println(nNode);
//            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element eElement = (Element) nNode;
//                if (request.equals("FormattedPrice")) {
//                  //  String totalResults = eElement.getElementsByTagName("FormattedPrice").getTextContent();
//                   // respond = totalResults;
//                }
//                if (request.equals("TotalPages")) {
//                    String totalPages = eElement.getElementsByTagName("TotalPages").item(0).getTextContent();
//                    respond = totalPages;
//                }
//                if (request.equals("MoreSearchResultsUrl")) {
//                    String moreSearchResultsUrl = eElement.getElementsByTagName("MoreSearchResultsUrl").item(0).getTextContent();
//                    respond = moreSearchResultsUrl;
//                }
//            }
//        }
//        return respond;
    }

    public String itemTitle(Document doc, String request) throws XPathExpressionException {

        XPath xPath = XPathFactory.newInstance().newXPath();
        String mediumImage = "/ItemLookupResponse/Items/Item/ItemAttributes";
        NodeList nodeMediumImage = (NodeList) xPath.compile(mediumImage).evaluate(doc, XPathConstants.NODESET);
        //  System.out.println(nodeMediumImage.getLength());
        String respond = null;
        for (int i = 0; i < nodeMediumImage.getLength(); i++) {
            Node nNode = nodeMediumImage.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                respond = eElement.getElementsByTagName("Title").item(0).getTextContent();
            }
        }
        return respond;

    }

    public String itemLink(Document doc) throws XPathExpressionException {

        XPath xPath = XPathFactory.newInstance().newXPath();
        String mediumImage = "/ItemLookupResponse/Items/Item";
        NodeList nodeMediumImage = (NodeList) xPath.compile(mediumImage).evaluate(doc, XPathConstants.NODESET);
        //    System.out.println(nodeMediumImage.getLength());
        String respond = null;
        for (int i = 0; i < nodeMediumImage.getLength(); i++) {
            Node nNode = nodeMediumImage.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                respond = eElement.getElementsByTagName("DetailPageURL").item(0).getTextContent();
            }
        }
        return respond;

    }

    public int rank(Document doc) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String mediumImage = "/ItemLookupResponse/Items/Item";
        NodeList nodeMediumImage = (NodeList) xPath.compile(mediumImage).evaluate(doc, XPathConstants.NODESET);
        //    System.out.println(nodeMediumImage.getLength());
        int respond = 0;

        XPathExpression expr = xPath.compile("/ItemLookupResponse/Items/Item/SalesRank");
        Object result = expr.evaluate(doc, XPathConstants.NODE);
        //System.out.println(result);
        if (result != null) {
            for (int i = 0; i < nodeMediumImage.getLength(); i++) {
                Node nNode = nodeMediumImage.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //mc:CreditReport/mc:AttachedXml)
                    respond = Integer.parseInt(eElement.getElementsByTagName("SalesRank").item(0).getTextContent());
                }
            }
        }

        return respond;
    }
    public int itemSalePrice(Document doc) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String mediumImage = "/ItemLookupResponse/Items/Item";
        NodeList nodeMediumImage = (NodeList) xPath.compile(mediumImage).evaluate(doc, XPathConstants.NODESET);
        //    System.out.println(nodeMediumImage.getLength());
        int respond = 0;

        XPathExpression expr = xPath.compile("/ItemLookupResponse/Items/Item/SalesRank");
        Object result = expr.evaluate(doc, XPathConstants.NODE);
        //System.out.println(result);
        if (result != null) {
            for (int i = 0; i < nodeMediumImage.getLength(); i++) {
                Node nNode = nodeMediumImage.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //mc:CreditReport/mc:AttachedXml)
                    respond = Integer.parseInt(eElement.getElementsByTagName("SalesRank").item(0).getTextContent());
                }
            }
        }

        return respond;
    }
    public int itemAmount(Document doc) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String mediumImage = "/ItemLookupResponse/Items/Item";
        NodeList nodeMediumImage = (NodeList) xPath.compile(mediumImage).evaluate(doc, XPathConstants.NODESET);
        int respond = 0;
        XPathExpression expr = xPath.compile("/ItemLookupResponse/Items/Item/Offers/Offer/OfferListing/Price/Amount");
        Object result = expr.evaluate(doc, XPathConstants.NODE);
        //System.out.println(result);
        if (result != null) {
            for (int i = 0; i < nodeMediumImage.getLength(); i++) {
                Node nNode = nodeMediumImage.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //mc:CreditReport/mc:AttachedXml)
                    respond = Integer.parseInt(eElement.getElementsByTagName("Amount").item(0).getTextContent());
                }
            }
        }
        System.out.println("Amount is: " +respond);
        return respond;

//        XPath xPath = XPathFactory.newInstance().newXPath();
//        String mediumImage = "/ItemLookupResponse/Items/Item/Offers/Offer/OfferListing/Price";
//        NodeList nodeMediumImage = (NodeList) xPath.compile(mediumImage).evaluate(doc, XPathConstants.NODESET);
//        //    System.out.println(nodeMediumImage.getLength());
//        int respond = 0;
//        for (int i = 0; i < nodeMediumImage.getLength(); i++) {
//            Node nNode = nodeMediumImage.item(i);
//            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element eElement = (Element) nNode;
//                respond = Integer.parseInt(eElement.getElementsByTagName("Amount").item(0).getTextContent());
//            }
//        }
//        return respond;

    }



    public String productGroup(Document doc) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String mediumImage = "/ItemLookupResponse/Items/Item";
        NodeList nodeMediumImage = (NodeList) xPath.compile(mediumImage).evaluate(doc, XPathConstants.NODESET);
        //    System.out.println(nodeMediumImage.getLength());
        String respond = null;

        XPathExpression expr = xPath.compile("/ItemLookupResponse/Items/Item/ItemAttributes/ProductGroup");
        Object result = expr.evaluate(doc, XPathConstants.NODE);
        //System.out.println(result);
        if (result != null) {
            for (int i = 0; i < nodeMediumImage.getLength(); i++) {
                Node nNode = nodeMediumImage.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //mc:CreditReport/mc:AttachedXml)
                    respond = eElement.getElementsByTagName("ProductGroup").item(0).getTextContent();
                }
            }
        }

        return respond;
    }

}