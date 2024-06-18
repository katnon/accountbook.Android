package com.cookandroid.finalproject;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainMenuHomeFragment fragmentHome = new MainMenuHomeFragment();

    private MainMenuInputFragment fragmentInput = new MainMenuInputFragment();
    private MainMenuReportFragment fragmentReport = new MainMenuReportFragment();
    private MainMenuSearchFragment fragmentSearch = new MainMenuSearchFragment();

    Button btnWrite = fragmentInput.btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, fragmentHome).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());



    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            int itemId = menuItem.getItemId();
            if (itemId == R.id.menu_home) {
                // menu_chart에 대한 작업 수행
                transaction.replace(R.id.menu_frame_layout, fragmentHome).commitAllowingStateLoss();
//                        break;
            } else if (itemId == R.id.menu_input) {
                // menu_search에 대한 작업 수행
                transaction.replace(R.id.menu_frame_layout, fragmentInput).commitAllowingStateLoss();
//                        break;
            } else if (itemId == R.id.menu_report) {
                // menu_more에 대한 작업 수행
                transaction.replace(R.id.menu_frame_layout, fragmentReport).commitAllowingStateLoss();
            }
//                        break;
//            } else if (itemId == R.id.menu_search) {
//                // menu_search에 대한 작업 수행
//                transaction.replace(R.id.menu_frame_layout, fragmentSearch).commitAllowingStateLoss();
////                        break;
//            }

            return true;

        }
    }


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//
//
//
//
//
//    }
}