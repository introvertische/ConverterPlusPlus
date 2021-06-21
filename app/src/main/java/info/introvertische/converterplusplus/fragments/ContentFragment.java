package info.introvertische.converterplusplus.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.TreeMap;

import info.introvertische.converterplusplus.R;
import info.introvertische.converterplusplus.currency.GetCurrencyRate;

public class ContentFragment extends Fragment {

    static Spinner spinnerIn;
    static View root;

    static TreeMap<String, String> map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //TODO Implement saving data from the Internet to a file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);
        root = rootView;

        spinnerIn = rootView.findViewById(R.id.spinnerIn);

        new GetDataTask().execute("https://cbr.ru/currency_base/daily/");


        return rootView;
    }

    private static void addDataToSpinner(List<String> data) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIn.setAdapter(adapter);
    }

    public static class GetDataTask extends AsyncTask<String, Void, List<String>> {

        ProgressDialog dialog = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(root.getContext(), "",
                    "Loading. Please wait...", true);
        }

        @Override
        protected List<String> doInBackground(String... url) {
            GetCurrencyRate getCurrencyRate = new GetCurrencyRate(url[0]);
            map = getCurrencyRate.currencyDataMap;
            return getCurrencyRate.createList();
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            addDataToSpinner(strings);
            dialog.dismiss();
        }
    }
}