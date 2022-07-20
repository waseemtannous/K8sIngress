package com.waseemtannous.ynetnews;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


@Controller
public class IndexController {

    String url = "http://www.ynet.co.il/Integration/StoryRss2.xml";

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("news", this.getNews());
        return "index";
    }

    public ArrayList<ArrayList<String>> getNews() {
        ArrayList<ArrayList<String>> allNews = new ArrayList<>();

//        request xml file from link and parse it to list of objects and save them to variable
        try {
            NodeList news = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.url).getElementsByTagName("item");

            for (int i = 0; i < news.getLength(); i++) {
                Node item = news.item(i);
                NodeList children = item.getChildNodes();

                ArrayList<String> newsItem = new ArrayList<>();


                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    switch (child.getNodeName()) {
                        case "title":
                        case "link":
                            newsItem.add(child.getTextContent());
                            break;
                        case "description":
                            String content = child.getTextContent();

//                            find </div> substring and remove it
                            if (content.length() > 0) {
                                newsItem.add(content.substring(content.indexOf("</div>") + 6));
                                newsItem.add(content.substring(content.indexOf("src") + 5, content.indexOf("' alt")));
                            } else {
                                newsItem.add(" ");
                            }
                            break;

                        case "pubDate":
                            newsItem.add(child.getTextContent().substring(0, 25));
                            break;
                    }
                }
                allNews.add(newsItem);
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return allNews;
    }
}
