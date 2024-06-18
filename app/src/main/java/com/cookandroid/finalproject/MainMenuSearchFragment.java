package com.cookandroid.finalproject;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuSearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public MainMenuSearchFragment() {
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
    public static MainMenuSearchFragment newInstance(String param1, String param2) {
        MainMenuSearchFragment fragment = new MainMenuSearchFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_menu_search,container,false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String[] data = bundle.getStringArray("key");
            // 이제 'data' 배열을 사용할 수 있습니다.
        }

        final ArrayList<String> dataList = new ArrayList<>();

//        String[] items = new String[dataAll.length];
////        String[] itemValues = new String[textDataAll.length()];
////        String[] itemDates =  new String[textDataAll.length()];
//        for (int i = 0; i < dataAll.length; i++)
//        {
//
////            itemValues[i] =dataAll[i][1];
////            itemDates[i] =dataAll[i][2];
//            dataList.add(dataAll[i][1]);
//        }
//
//// ArrayAdapter 객체를 생성합니다.
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, dataList);
//
//// 어댑터를 ListView에 설정합니다.
//        dataListView.setAdapter(adapter);
//
//// ListView 객체의 특정 아이템 클릭시 처리를 추가합니다.
//        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                // 클릭한 아이템의 문자열을 가져옵니다.
//                String selected_item = adapterView.getItemAtPosition(position).toString();
//
//                // 해당 아이템을 ArrayList 객체에서 제거합니다.
//                //items.remove(selected_item);
//
//                // 어댑터 객체에 변경 내용을 반영합니다.
//                adapter.notifyDataSetChanged();
//            }
//        });

//        try {
//            // 파일 읽기
//            FileInputStream inFs = getActivity().openFileInput("file.txt");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inFs));
//            List<String> lines = new ArrayList<>();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                lines.add(line);
//            }
//            reader.close();
//
//            // 줄 삭제
//            String targetId = "123"; // 삭제하려는 줄의 고유 번호
//            for (Iterator<String> iterator = lines.iterator(); iterator.hasNext();) {
//                String currentLine = iterator.next();
//                if (currentLine.startsWith(targetId + ":")) {
//                    iterator.remove();
//                    break;
//                }
//            }
//
//            // 파일 쓰기
//            FileOutputStream outFs = getActivity().openFileOutput("file.txt", Context.MODE_PRIVATE);
//            PrintWriter writer = new PrintWriter(outFs);
//            for (String l : lines) {
//                writer.println(l);
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        return rootView;
    }
}