package jsoup.html;
import java.io.*;

public class ASINListCreater {

    public static void fileWriter() throws IOException {
        HtmlParser jParser =new HtmlParser();
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please copy/paste url you need to to parse:");
        String url = bufferRead.readLine();
        jParser.setUrl(url);
        System.out.println();
        String asin[]=jParser.asis();
        File file = new File("ASINs.txt");
        PrintWriter out = new PrintWriter(new FileWriter(file));
        for (String s : asin) {
            if(s!=null) out.println(s);
        }
        out.close();

    }
}
