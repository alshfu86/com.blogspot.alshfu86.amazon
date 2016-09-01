package jsoup.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

@SuppressWarnings("ALL")
public class HtmlParser {
    static int doneCounter = 0;
    static boolean isDone = false;

    int ARRAY_SIZE = 300000;
    String url = null;
    Elements elements;
    Elements element = null;
    String title = null;
    String asis[] = new String[ARRAY_SIZE];


    public void setUrl(String url) {
        this.url = url;
    }

    public String[] asis() throws IOException {
        Document doc = doc(url);
        elements = doc.getElementsByTag("li");
        asinGetter();
        for (int j = 0; j <400; j++) {
            doc = doc(nextPage(doc));
            elements = doc.getElementsByTag("li");
            asinGetter();
        }
        System.out.println(nextPage(doc));
        return asis;
    }

    static int i = 0;

    private void asinGetter() throws IOException {

            String attr = null;
                for (Element element1 : elements) {
                    attr = element1.attr("data-asin");
                    if (attr.isEmpty()) {
                    } else {
                        asis[i] = attr;
                        System.out.println(attr);
                        i++;
                    }
                    isDone(attr);
                }
    }

    private String nextPage(Document doc) {
        element = doc.getElementsByAttributeValue("id", "pagnNextLink");
        url = "https://www.amazon.com" + element.attr("href");
        return url;
    }

    private Document doc(String url) throws IOException {
        Document doc = Jsoup.connect(url).userAgent("Mozila").get().normalise();
        return doc;
    }

    private String getUrl() {
        return url;
    }

    private boolean isDone(String toControl) {

        if (toControl.isEmpty()) {
            doneCounter++;
        }
        if (doneCounter == 10) {
            isDone = true;
        }
        //System.out.println(doneCounter);
        return isDone;
    }

}
