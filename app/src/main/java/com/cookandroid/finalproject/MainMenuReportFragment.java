package com.cookandroid.finalproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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

    private String[][] myArray;

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

        Button deleteRowButton = (Button) rootView.findViewById(R.id.btnEditRow);
        EditText editDelRow = (EditText) rootView.findViewById(R.id.editDelRow);
        String str = editDelRow.getText().toString();
        deleteRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editDelRow.getText().toString();
                try {
                    Integer number = Integer.valueOf(str);
                    System.out.println(number);

                    // 여기에서 행을 삭제합니다.
                    deleteRow(number);
                    onStart();
                    onResume();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });

                    return rootView;

    }

    private void deleteRow(int row) {
        String[][] newArray = new String[dataAll.length - 1][];
        System.arraycopy(dataAll, 0, newArray, 0, row);
        System.arraycopy(dataAll, row + 1, newArray, row, dataAll.length - row - 1);
        dataAll = newArray;

        try {
            FileOutputStream outFs = requireContext().openFileOutput("file.txt", Context.MODE_PRIVATE);


            String str = "";
            //lineCount ++;
            for (int j = 0 ; j < dataAll.length; j++) {
                for (int i = 0; i < dataAll[j].length; i++) {
                    str += dataAll[j][i];
                    // 마지막 요소가 아닌 경우에만 쉼표를 추가합니다.
                    if (i < dataAll[j].length - 1) {
                        str += ",";
                    }
                    else{
                        str += "\n";
                    }
                }
                // 각 행을 새 줄에 씁니다.

            }

            //파일에 한 건당 한 줄씩 이어서 저장


            outFs.write(str.getBytes());
            outFs.close();

            Toast.makeText(requireContext(), "삭제 내용이 적용되었습니다.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {}


    }

    @Override
    public void onStart() {
        super.onStart();

        try {


                FileInputStream inFs = getActivity().openFileInput("file.txt");
                int fileSize = inFs.available();
                byte[] txt = new byte[fileSize];

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

        textDataAll.setText("");

        for (int i=0; i < dataAll.length; i++){
            for (int j=0; j < dataAll[i].length;j++){
                textDataAll.setText(textDataAll.getText() +","+ dataAll[i][j]);
            }
            textDataAll.setText(textDataAll.getText() + "\n");
        }

//        Bundle bundle = new Bundle();
//        bundle.putStringArray("key", dataAll);

//



    }

    private FileInputStream openFileInput(String file) {
        return null;
    }
}