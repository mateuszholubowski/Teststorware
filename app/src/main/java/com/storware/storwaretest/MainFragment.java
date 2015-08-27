package com.storware.storwaretest;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Scanner;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemClickListener{

    public static final String TAG = "MainFragment";

    public MainFragment() {
        // Required empty public constructor
    }

    @InjectView(R.id.list_view)
    ListView assetListView;
    AssetsListAdapter mAdapter;
    AssetManager assetManager;

    @InjectView(R.id.result_txt)
    TextView resultTxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View contentView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, contentView);

        assetListView.setOnItemClickListener(this);
        try {
            assetManager = getActivity().getAssets();
            String[] assets = assetManager.list("");
            mAdapter = new AssetsListAdapter(assets);
            assetListView.setAdapter(mAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        try {
            processFile((String) parent.getAdapter().getItem(position));
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Cant read file", Toast.LENGTH_LONG).show();
        }
    }


    public void processFile(String fileName) throws IOException {

        Scanner scanner = new Scanner(assetManager.open(fileName));
        Reader reader = new Reader(scanner);
        Processor processor = new Processor(reader, SimpleCalculator.getInstance());
        String result = null;
        try {
            result = processor.calculate();
        } catch (Reader.ParseException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        resultTxt.setText(result);
    }


    class AssetsListAdapter extends BaseAdapter {

        private String[] assets;
        private LayoutInflater inflater;

        AssetsListAdapter(String[] assets) {
            this.assets = assets;
            inflater = LayoutInflater.from(getActivity());
            if (assets == null)
                assets = new String[0];
        }

        @Override
        public int getCount() {
            return assets.length;
        }

        @Override
        public Object getItem(int position) {
            return assets[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = inflater.inflate(R.layout.asset_list_row, parent, false);
            }

            TextView view = (TextView) convertView.findViewById(R.id.asset_name_Txt);
            view.setText(assets[position]);

            return convertView;
        }
    }

}
