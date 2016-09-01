package HTMLCreator;

/**
 * Created by User on 27.08.2016.
 */
public class ToWrite {
    String image;
    private String asin;
    private String height;
    private String length;
    private String weight;
    private String width;
    private String price;
    private String rank;
    private String title;
    String toAdd="<td style=\"width: 33.005%;\"><img src=\""+image+"\" alt=\"Mountain View\" style=\"width:304px;height:228px;\"></td>\n" +
            "<td style=\"width: 65.995%;\">\n" +
            "<p>Title:"+title+"</p>\n" +
            "<table style=\"width: 530px; height: 256px;\">\n" +
            "<tbody>\n" +
            "<tr style=\"height: 25px;\">\n" +
            "<td style=\"width: 529px; height: 25px;\">ASIN:"+asin+"</td>\n" +
            "</tr>\n" +
            "<tr style=\"height: 25px;\">\n" +
            "<td style=\"width: 529px; height: 25px;\">Height:"+height+"</td>\n" +
            "</tr>\n" +
            "<tr style=\"height: 25px;\">\n" +
            "<td style=\"width: 529px; height: 25px;\">Length:"+length+"</td>\n" +
            "</tr>\n" +
            "<tr style=\"height: 25px;\">\n" +
            "<td style=\"width: 529px; height: 25px;\">Weight:"+weight+"</td>\n" +
            "</tr>\n" +
            "<tr style=\"height: 25px;\">\n" +
            "<td style=\"width: 529px; height: 25px;\">Width:"+width+"</td>\n" +
            "</tr>\n" +
            "<tr style=\"height: 26px;\">\n" +
            "<td style=\"width: 529px; height: 26px;\">Price:"+price+"</td>\n" +
            "</tr>\n" +
            "<tr style=\"height: 26px;\">\n" +
            "<td style=\"width: 529px; height: 26px;\">SalesRank:"+rank+"</td>\n" +
            "</tr>\n" +
            "<tr style=\"height: 26px;\">\n" +
            "<td style=\"width: 529px; height: 26px;\">&nbsp;</td>\n" +
            "</tr>\n" +
            "<tr style=\"height: 26px;\">\n" +
            "<td style=\"width: 529px; height: 26px;\">&nbsp;</td>\n" +
            "</tr>\n" +
            "<tr style=\"height: 26px;\">\n" +
            "<td style=\"width: 529px; height: 26px;\">&nbsp;</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "</td>";

    public ToWrite(String toAdd) {
        this.toAdd = toAdd;
    }
}
