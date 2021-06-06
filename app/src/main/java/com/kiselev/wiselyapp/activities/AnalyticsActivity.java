package com.kiselev.wiselyapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.Spend_CommentDAO;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.dao.Spend_TypeDAO;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.fragment.FragmentPie;
import com.kiselev.wiselyapp.fragment.FragmentBar;

import java.util.ArrayList;
import java.util.List;


public class AnalyticsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    Spend_IncomeDAO spend_incomeDAO;
    TypeDAO typeDAO;
    Spend_TypeDAO spend_typeDAO;
    Spend_CommentDAO spend_commentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        AppDatabase db = DBHelper.getInstance().getDatabase();

        typeDAO = db.typeDAO();

        spend_incomeDAO = db.spend_incomeDAO();
        spend_typeDAO = db.spend_typeDAO();
        spend_commentDAO = db.spend_commentDAO();

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Траты по дням");
        arrayList.add("Траты по категориям");

        prepareViewPager(viewPager, arrayList);

        tabLayout.setupWithViewPager(viewPager);


        addListenerOnButton();
    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        FragmentBar fragmentbar = new FragmentBar();
        /*Bundle bundle = new Bundle();
        bundle.putString("title", arrayList.get(0));*/
        //fragmentOne.setArguments(bundle);
        adapter.addFragment(fragmentbar, arrayList.get(0));
        /////////////////////
        FragmentPie fragmentPie = new FragmentPie();
        Bundle bundle = new Bundle();
        bundle.putString("title", arrayList.get(0));

        fragmentPie.setArguments(bundle);
        adapter.addFragment(fragmentPie, arrayList.get(1));


        viewPager.setAdapter(adapter);

    }

    public void addListenerOnButton(){

    }

    private class MainAdapter extends FragmentPagerAdapter{

        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        public void addFragment(Fragment fragment, String title){
            arrayList.add(title);
            fragmentList.add(fragment);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrayList.get(position);
        }
    }
}