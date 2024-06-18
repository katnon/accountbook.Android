package com.cookandroid.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuReportFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    TextView textDataAll;
    String[][] dataAll;
    ListView dataListView;

    public MainMenuReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuReportFragment newInstance(String param1, String param2) {
        MainMenuReportFragment fragment = new MainMenuReportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_menu_report,container,false);

        textDataAll = (TextView) rootView.findViewById(R.id.textDataAll);

        dataListView = (ListView) getActivity().findViewById(R.id.dataListView);

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            FileInputStream inFs = getActivity().openFileInput("file.txt");
            byte[] txt = new byte[9999];

            inFs.read(txt);

            String str = new String(txt);

              String[] row = str.split("\n");
            dataAll= new String[row.length][];

            for (int i = 0; i < row.length; i++) {
                String[] columns = row[i].split(",");
                dataAll[i] = new String[columns.length];
                for (int j = 0; j < columns.length; j++) {
                    dataAll[i][j] = columns[j];
                }
            }

            inFs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onResume() {
        super.onResume();

        for (int i=0; i < dataAll.length; i++){
            for (int j=0; j < dataAll[i].length;j++){
                textDataAll.setText(textDataAll.getText() +","+ dataAll[i][j]);
            }
            textDataAll.setText(textDataAll.getText() + "\n");
        }

//        Bundle bundle = new Bundle();
//        bundle.putStringArray("key", dataAll);

//        MainMenuSearchFragment mainMenuSearchFragment = new MainMenuSearchFragment();
//        mainMenuSearchFragment.setArguments(bundle);



    }

    private FileInputStream openFileInput(String file) {
        return null;
    }
}