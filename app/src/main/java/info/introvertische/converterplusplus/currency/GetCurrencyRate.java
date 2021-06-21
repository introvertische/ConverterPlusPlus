package info.introvertische.converterplusplus.currency;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import info.introvertische.converterplusplus.GetData;


public class GetCurrencyRate extends GetData {

    public TreeMap<String, String> currencyDataMap;

    private final String attributeTr;
    private final String attributeTd;

    {
        attributeTr = "tr";
        attributeTd = "td";
    }

    public GetCurrencyRate(String url) {
        currencyDataMap = parse(connect(url));
    }


    @Override
    protected TreeMap<String, String> parse(Document document) {
        Elements elements = document.select(attributeTr);

        List<List<String>> data = new ArrayList<>();
        for (Element elementTr : elements) {
            List<String> _data = new ArrayList<>();
            for (Element elementTd : elementTr.select(attributeTd)) {
                _data.add(elementTd.text());
            }
            if (_data.size() != 0) data.add(_data);
        }

        return createHashMap(data);
    }

    private TreeMap<String, String> createHashMap(List<List<String>> data) {
        TreeMap<String, String> dataMap = new TreeMap<>();
        int positionName = 3;
        int positionRate = 4;

        for (List<String> currency : data) {
            dataMap.put(currency.get(positionName), currency.get(positionRate));
        }

        return dataMap;
    }

    public List<String> createList() {
        return new ArrayList<>(currencyDataMap.keySet());
    }

}
