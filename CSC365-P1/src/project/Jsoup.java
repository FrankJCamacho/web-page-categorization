package project;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Jsoup {

    //mw-parser-output
    //p paragraphs
    //something that references information pulled from a wikipedia page

    public static String jsoupText(String url) throws IOException {
        Document doc = org.jsoup.Jsoup.connect(url)
                .timeout(6000).get();

        Elements body = doc.select("div.mw-parser-output");
        StringBuilder paragraph = new StringBuilder();
        for(Element e :body.select("p")) {

            paragraph.append(e.text());
        }
        return paragraph.toString();
    }

}
