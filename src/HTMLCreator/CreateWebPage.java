package HTMLCreator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CreateWebPage {
    String image;
    private String asin;
    private String height;
    private String length;
    private String weight;
    private String width;
    private String price;
    private String rank;
    private String title;
    private String itemLink;
    private String productGroup;

    public String getProductGroup() {
        return productGroup;
    }
    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setAsin(String asin) {
        this.asin = asin;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }



    public void pageCrater() {
        String a = "<a href=\"" + itemLink + "\">" + title + "</a>";
        String endOfTable = "</td>\n</tr>\n</tbody>\n</table>\n</td>\n</tr>\n</tbody>\n</table>\n</td>\n</tr>\n</tbody>\n</table>";
        String tr25 = "<tr style=\"height: 25px;\">\n <td style=\"width: 529px; height: 25px;\">";
        String trEnd = "</td>\n</tr>\n";
        String imageTag = "<img src=\"" + image + "\" alt=\"Mountain View\" style=\"width:160px;height:160px;\">";
        String begunTable = "\n <table style=\"width: 100%;\" border=\"2\" cellpadding=\"2\">\n<tbody><td style=\"width: 15%;\">";
        String endofImageFrame = "</td>\n<td \">\n<table style=\"width: 530px; height: 256px;\">\n<tbody >";
        String toAdd =
                begunTable + imageTag + endofImageFrame +
                        tr25 + "Title: " + a + trEnd +
                        tr25 + "ASIN:" + asin + trEnd +
                        tr25 + "Height:" + height + trEnd +
                        tr25 + "Length:" + length + trEnd +
                        tr25 + "Width:" + width + trEnd +
                        tr25 + "Weight:" + weight + trEnd +
                        tr25 + "Price:" + price + trEnd +
                        tr25 + "SalesRank:" + rank + " Product Group is: " + productGroup +
                        endOfTable;
        String filePath = "list.html";

        appendUsingOutputStream(filePath, toAdd);
    }
    private static void appendUsingOutputStream(String fileName, String data) {
        OutputStream os = null;
        try {
            //в конструкторе FileOutputStream используем флаг true, который обозначает обновление содержимого файла
            os = new FileOutputStream(new File(fileName), true);
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}