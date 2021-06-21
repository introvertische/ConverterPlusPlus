package info.introvertische.converterplusplus.currency;

import android.os.AsyncTask;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Converter {

    private String url;
    private Document document;
    List<List<String>> list = new ArrayList<>();

    {
        url = "https://cbr.ru/currency_base/daily/";
        //new ConnectionTask().execute();
    }

    private void connect() throws IOException {
        document = Jsoup.connect(url).get();
    }

    private void getData() {
        Elements elements = document.select("td");

        for (Element element : elements) {
            List<String> list1 = new ArrayList<>();
            for( Element element1 : element.select("td")) {
                list1.add(element1.toString());
            }
            list.add(list1);
        }
    }

    public String[] get() {
        getData();
        String[] str = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i).get(3);
        }

        return str;
    }
}
