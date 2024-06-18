package com.cookandroid.finalproject;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuHomeFragment extends Fragment {

    private CalendarView calendarView;
    private Spinner spinnerYearMonth;


    private TextView tvDate;
    private GridAdapter gridAdapter;
    private ArrayList<String> dayList;
    private GridView gridView;
    private Calendar mCal;
    private Context mContext;
    String[][] dataAll;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenuHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuHomeFragment newInstance(String param1, String param2) {
        MainMenuHomeFragment fragment = new MainMenuHomeFragment();
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
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_menu_home, container, false);

        tvDate = (TextView) rootView.findViewById(R.id.tv_date);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);


        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);
        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌려줌
        if (tvDate != null)
            tvDate.setText(curYearFormat.format(date) + "년 " + curMonthFormat.format(date) + "월 ");

        //gridview 요일 표시
        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");

        mCal = Calendar.getInstance();

        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);

        gridAdapter = new GridAdapter(getContext(), dayList);
        if (gridView != null) {
            gridView.setAdapter(gridAdapter);
        }


        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 날짜를 가져옵니다.
                final Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int day = currentDate.get(Calendar.DAY_OF_MONTH);

                // DatePickerDialog를 생성하고 표시합니다.
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // 사용자가 선택한 날짜를 TextView에 설정합니다.
                                String selectedDate = year + "년 " + (monthOfYear + 1) + "월 ";
                                tvDate.setText(selectedDate);

                                // Clear the old calendar data
                                dayList.clear();

                                // Add days of week
                                dayList.add("일");
                                dayList.add("월");
                                dayList.add("화");
                                dayList.add("수");
                                dayList.add("목");
                                dayList.add("금");
                                dayList.add("토");

                                // Set the calendar to the selected date
                                mCal.set(year, monthOfYear, 1);
                                int dayNum = mCal.get(Calendar.DAY_OF_WEEK);

                                // Add empty days for the first week
                                for (int i = 1; i < dayNum; i++) {
                                    dayList.add("");
                                }

                                // Add the days of the month
                                setCalendarDate(mCal.get(Calendar.MONTH) + 1);

                                // Notify the adapter that the data has changed
                                gridAdapter.notifyDataSetChanged();
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        return rootView;
    }


    private void setCalendarDate(int month) {
        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add("" + (i + 1));
        }

    }

    /**
     * 그리드뷰 어댑터
     */
    private class GridAdapter extends BaseAdapter {

        private final List<String> list;

        private final LayoutInflater inflater;

        /**
         * 생성자
         *
         * @param context
         * @param list
         */
        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new ViewHolder();
                holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);
                holder.tvItemGridViewIncome = (TextView) convertView.findViewById(R.id.tv_item_gridview_income);
                holder.tvItemGridViewSpend = (TextView) convertView.findViewById(R.id.tv_item_gridview_spend);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvItemGridView.setText("" + getItem(position));
            holder.tvItemGridViewIncome.setText("");
            holder.tvItemGridViewSpend.setText("");

            // 파일에서 데이터를 가져옵니다.
            String[][] dataAll = null;
            try {
                FileInputStream inFs = getActivity().openFileInput("file.txt");
                int fileSize = inFs.available();
                // 파일이 비어 있지 않은 경우에만 읽습니다.
                if (fileSize != 0) {
                    byte[] txt = new byte[fileSize];
                    inFs.read(txt);
                    String str = new String(txt);
                    String[] row = str.split("\n");
                    dataAll = new String[row.length][];
                    for (int i = 0; i < row.length; i++) {
                        String[] columns = row[i].split(",");
                        dataAll[i] = new String[columns.length];
                        System.arraycopy(columns, 0, dataAll[i], 0, columns.length);
                    }
                }
                inFs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 데이터가 있는 경우에만 각 행을 확인합니다.
            if (dataAll != null) {
                for (String[] row : dataAll) {
                    // 파일의 날짜가 그리드의 날짜와 일치하는 경우
                    if (row[3].equals(tvDate.getText().toString() + getItem(position) + "일")) {
                        try {
                            int value = Integer.parseInt(row[2]);
                            if  (row[0].equals("수입")) {
                                holder.tvItemGridViewIncome.append("+" + numberFormat.format(value));
                            } else {
                                holder.tvItemGridViewSpend.append("-" + numberFormat.format(value));
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();

                        }
                    }
                }
            }
            return convertView;
        }

    }

    class ViewHolder {
        public TextView tvItemGridViewIncome;
        public TextView tvItemGridViewSpend;
        TextView tvItemGridView;
    }
}
