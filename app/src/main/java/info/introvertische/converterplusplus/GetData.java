package info.introvertische.converterplusplus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.TreeMap;

import info.introvertische.converterplusplus.interfaces.IConnect;

public abstract class GetData implements IConnect {

    @Override
    public Document connect(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException exp) {
            //System.exit(1);
        }
        return document;
    }

    protected abstract TreeMap<String, String> parse(Document document);
}
