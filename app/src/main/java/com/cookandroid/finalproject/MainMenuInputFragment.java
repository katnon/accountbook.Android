package com.cookandroid.finalproject;

import static kotlin.io.ConsoleKt.readLine;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Selection;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */





public class MainMenuInputFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenuInputFragment() {
        // Required empty public constructor

    }
    Button btnWrite;
    EditText editName, editValue, editPayment, editMemo;
    TextView textName, textValue, titleTextDate, textPayment, textDate, textMemo;
    ImageButton imageButtonDate;

    final Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    int lineCount = 0;

    int curTab = 1; //지출 탭 기본값

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuMoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuInputFragment newInstance(String param1, String param2) {
        MainMenuInputFragment fragment = new MainMenuInputFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_menu_input,container,false);

        textName = (TextView) rootView.findViewById(R.id.textName);
        textValue = (TextView) rootView.findViewById(R.id.textValue);
        titleTextDate = (TextView) rootView.findViewById(R.id.titleTextDate);
        textPayment = (TextView) rootView.findViewById(R.id.textPayment);
        textMemo = (TextView) rootView.findViewById(R.id.textMemo);

        
        btnWrite = (Button) rootView.findViewById(R.id.writeButton);


        editName = (EditText)rootView.findViewById(R.id.editName);
        editValue = (EditText) rootView.findViewById(R.id.editValue);
        imageButtonDate = (ImageButton) rootView.findViewById(R.id.imageButtonDate);
        textDate = (TextView) rootView.findViewById(R.id.textDate);
        textDate.setText(Integer.toString(mYear)+"년 "+Integer.toString(mMonth +1)+"월 "+Integer.toString(mDay)+"일");
        EditText editPayment = (EditText) rootView.findViewById(R.id.editPayment);
        editMemo = (EditText) rootView.findViewById(R.id.editMemo);
        SpannableString content = new SpannableString(textDate.getText().toString());

        content.setSpan(new UnderlineSpan(), 0, content.length(),0);
        textDate.setText(content);

        try {
            lineCount = 0;
            String filePath = "/data/data/com.cookandroid.finalproject/files/file.txt"; // 파일 경로를 입력하세요.
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));

            while (br.readLine() != null) {
                lineCount++;
            }
            br.close();
        }catch (IOException e){}
        Toast.makeText(requireContext(), "line 수: " + lineCount, Toast.LENGTH_SHORT).show();


        TabLayout tabLayoutIP = (TabLayout) rootView.findViewById(R.id.tabLayoutIP);

        TabLayout.Tab tab = tabLayoutIP.getTabAt(1); // 'index'는 선택하려는 탭의 인덱스입니다.
        if (tab != null) {
            tab.select();
        }

        tabLayoutIP.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 탭이 선택되었을 때 실행할 코드를 여기에 작성합니다.
                // 예를 들어, 선택된 탭의 인덱스를 출력할 수 있습니다.

                switch (tab.getPosition()) {
                    case 0:
                        // 첫 번째 탭이 선택되었을 때 실행할 코드를 여기에 작성합니다.
                        textName.setText("수입명");
                        textValue.setText("수입 금액");
                        titleTextDate.setText("수입 일자");
                        textPayment.setText("수입 수단");
                        textPayment.setHint("수입 수단");
                        curTab = 0;
                        break;
                    case 1:
                        // 두 번째 탭이 선택되었을 때 실행할 코드를 여기에 작성합니다.
                        textName.setText("지출명");
                        textValue.setText("지출 금액");
                        titleTextDate.setText("지출 일자");
                        textPayment.setText("지출 수단");
                        textPayment.setHint("지출 수단");
                        curTab = 1;
                        break;
                    // 필요한 만큼 case를 추가합니다.
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // 탭이 선택 해제되었을 때 실행할 코드를 여기에 작성합니다.
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // 이미 선택된 탭이 다시 선택되었을 때 실행할 코드를 여기에 작성합니다.
            }
        });




        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editName.getText().toString().equals("") || editValue.getText().toString().equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    //builder.setTitle("AlertDialog Title");
                    builder.setMessage("빈 값이 있는지 확인해주세요!\n (필수 항목: 이름, 금액, 일자)");

                    // '확인' 버튼을 추가하고, 클릭 이벤트를 처리합니다.
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // '확인' 버튼을 클릭했을 때의 동작을 여기에 작성합니다.
                        }
                    });

                    // AlertDialog를 생성하고 표시합니다.
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    try {
                        FileOutputStream outFs = requireContext().openFileOutput("file.txt", Context.MODE_APPEND);

                        String str = "";
                        //lineCount ++;

                        if (curTab == 0)
                        {
                        str += "수입" + ",";
                        }
                        else if (curTab ==1)
                        {
                            str += "지출" + ",";
                        }

                        //파일에 한 건당 한 줄씩 이어서 저장

                        str += editName.getText() + ",";
                        str += editValue.getText().toString().replaceAll("[^0-9]","") + ",";
                        str += (Integer.toString(mYear)+"년 "+String.format("%02d",(mMonth + 1))+"월 "+String.format("%02d",(mDay)) + "일,");
                        if (editPayment.getText().toString().equals(""))
                        {
                            editPayment.setText("없음");
                        }
                        str += editPayment.getText()+",";
                        if (editMemo.getText().toString().equals(""))
                        {
                            editMemo.setText("없음");
                        }
                        str += editMemo.getText() + ","+"\n";

                        outFs.write(str.getBytes());
                        outFs.close();

                        Toast.makeText(requireContext(), "내용이 저장되었습니다.", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                    }
                }

            }
        });

        editValue.addTextChangedListener(new CustomTextWatcher(editValue));

        imageButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String str= mYear + "." + mMonth + "." + mDay;
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        (datePicker, year, month, day) -> {
                            // 날짜 선택 후 이뤄질 작업 작성
                            String selectedDate = year + "년 " + (month+1) + "월 " + day + "일";
                            textDate.setText(selectedDate);
                            mYear = year; mMonth = month; mDay = day;
                        }, mYear, mMonth, mDay);

// 스피너와 캘린더뷰를 같이 보여주기 때문에 캘린더 뷰 안보이게 설정
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
// 뒷배경이 투명한 다이얼로그 생성
                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();

            }
        });

//        // LayoutInflater 사용해 Resource Layout을 View로 변환해준 후 findViewById() 호출
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_main_menu_input, container, false);
//        // 보기 항목으로 사용할 데이터
//        String[] items = {"신용카드", "현금", "계좌이체", "Item4"};
//        // ArrayAdapter 생성
//
//
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, items);
//        // AutoCompleteTextView에 어댑터 설정
//        AutoCompleteTextView autoCompletePayment = (AutoCompleteTextView) getActivity().findViewById(R.id.autoCompletePayment);
//        autoCompletePayment.setAdapter(adapter);

        editPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence[] items;
                // AlertDialog.Builder 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                if (tabLayoutIP.getSelectedTabPosition() == 0)
                {
                    items = new CharSequence[]{"현금", "계좌이체", "기타"};
                    builder.setTitle("수입 수단 선택");
                }
                else
                {
                    items = new CharSequence[]{"신용카드", "체크카드", "현금", "계좌이체", "기타"};
                    builder.setTitle("지출 수단 선택");
                }


                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 항목을 클릭하면 EditText의 텍스트를 선택한 항목으로 설정
                        editPayment.setText(items[which]);
                    }
                });

                // AlertDialog 생성 및 표시
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



        return rootView;
    }




    public class CustomTextWatcher implements TextWatcher {

        private EditText editText;
        String strAmount = "";

        CustomTextWatcher(EditText et) {
            editText = et;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!TextUtils.isEmpty(s.toString()) && !s.toString().equals(strAmount)) {
                strAmount = makeStringComma(s.toString().replace(",", ""));
                editText.setText(strAmount);
                Editable editable = editText.getText();

                Selection.setSelection(editable, strAmount.length());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        protected String makeStringComma(String str) {    // 천단위 콤마설정.
            if (str.length() == 0) {
                return "";
            }
            long value = Long.parseLong(str);
            DecimalFormat format = new DecimalFormat("###,###");
            return format.format(value);
        }
    }







}
