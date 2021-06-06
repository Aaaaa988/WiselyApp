package com.kiselev.wiselyapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.kiselev.wiselyapp.R;
import com.kiselev.wiselyapp.database.AppDatabase;
import com.kiselev.wiselyapp.database.DBHelper;
import com.kiselev.wiselyapp.database.dao.Spend_CommentDAO;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.dao.Spend_TypeDAO;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.database.entity.Type;
import com.kiselev.wiselyapp.fragment.FragmentPie;
import com.kiselev.wiselyapp.fragment.FragmentBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class AnalyticsActivity extends AppCompatActivity {

    String[] monthNames = {
            "Январь",
            "Февраль",
            "Март",
            "Апрель",
            "Май",
            "Июнь",
            "Июль",
            "Август",
            "Сентябрь",
            "Октябрь",
            "Ноябрь",
            "Декабрь"
    };

    Button yearLess, yearMore, monthLess, monthMore;
    TextView year, month;

    TabLayout tabLayout;
    ViewPager viewPager;

    Spend_IncomeDAO spend_incomeDAO;
    TypeDAO typeDAO;
    Spend_TypeDAO spend_typeDAO;
    Spend_CommentDAO spend_commentDAO;

    TextView sumSpend, sumIncome, avrSpend;

    int positionTab = 0;

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

        sumIncome = (TextView) findViewById(R.id.sum_income);
        sumSpend = (TextView) findViewById(R.id.sum_spend);
        avrSpend = (TextView) findViewById(R.id.avg_spend);

        initTodayDate();

        outPutTabLayout(positionTab);
        outPutStatistic(String.valueOf(Arrays.asList(monthNames).indexOf(month.getText().toString())), year.getText().toString());

        addListenerOnButton();
    }

    private void outPutStatistic(String month, String year) {
        Double doubleIncome = spend_incomeDAO.getSummIncomeMonthYear(month, year);
        Double doubleSpend = spend_incomeDAO.getSummSpendMonthYear(month, year);
        Integer countSpend = spend_incomeDAO.getCountSpendMonthYear(month, year);

        if(doubleSpend != null){
            String spend = String.format(Locale.ENGLISH,"%.2f", doubleSpend);
            sumSpend.setText(spend +"р");

        }else{
            sumSpend.setText("Недостаточно информации");
        }
        if(doubleIncome != null){
            String income = String.format(Locale.ENGLISH,"%.2f", doubleIncome);
            sumIncome.setText(income +"р");
        }else{
            sumIncome.setText("Недостаточно информации");
        }

        if (countSpend != null && doubleSpend != null){
            String avr = String.format(Locale.ENGLISH,"%.2f", doubleSpend/countSpend);
            avrSpend.setText(avr +"р");
        }else{
            avrSpend.setText("Недостаточно информации");
        }

    }

    private void outPutTabLayout(int position) {
        String stringMonth = String.valueOf(Arrays.asList(monthNames).indexOf(month.getText().toString()));
        String date = "1/"+ stringMonth +"/"+ year.getText().toString();

        prepareViewPager(viewPager, date);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(position).select();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                positionTab = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void prepareViewPager(ViewPager viewPager, String date) {

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        FragmentBar fragmentBar = new FragmentBar();
        Bundle bundle = new Bundle();
        bundle.putString("date", date);
        fragmentBar.setArguments(bundle);
        adapter.addFragment(fragmentBar, "Траты по дням");
        /////////////////////
        FragmentPie fragmentPie = new FragmentPie();
        fragmentPie.setArguments(bundle);
        adapter.addFragment(fragmentPie, "Траты по категориям");


        viewPager.setAdapter(adapter);

    }


    private class MainAdapter extends FragmentStatePagerAdapter {

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

    private void initTodayDate() {
        year = (TextView)findViewById(R.id.year1_analytics);
        month = (TextView)findViewById(R.id.month1_analytics);
        Calendar calendar = Calendar.getInstance();

        year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        month.setText(monthNames[calendar.get(Calendar.MONTH)]);
    }

    public void addListenerOnButton(){
        yearLess = (Button)findViewById(R.id.yearLess_analytics);
        yearMore = (Button)findViewById(R.id.yearMore_analytics);
        monthLess = (Button)findViewById(R.id.monthLess_analytics);
        monthMore = (Button)findViewById(R.id.monthMore_analytics);

        year = (TextView)findViewById(R.id.year1_analytics);
        month = (TextView)findViewById(R.id.month1_analytics);

        yearLess.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int year_temp = Integer.parseInt(year.getText().toString());
                        if(year_temp > 1990 && year_temp <= 2100){
                            year_temp -= 1;
                            year.setText(String.valueOf(year_temp));

                            outPutTabLayout(positionTab);
                            outPutStatistic(String.valueOf(Arrays.asList(monthNames).indexOf(month.getText().toString())), year.getText().toString());
                        }

                    }
                }
        );

        yearMore.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int year_temp = Integer.parseInt(year.getText().toString());
                        if(year_temp >= 1990 && year_temp < 2100){
                            year_temp += 1;
                            year.setText(String.valueOf(year_temp));

                            outPutTabLayout(positionTab);
                            outPutStatistic(String.valueOf(Arrays.asList(monthNames).indexOf(month.getText().toString())), year.getText().toString());
                        }
                    }
                }
        );

        monthLess.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int month_temp = Arrays.asList(monthNames).indexOf(month.getText().toString());
                        month_temp -= 1;
                        if(month_temp < 0) month_temp = 11;
                        month.setText(monthNames[month_temp]);

                        outPutTabLayout(positionTab);
                        outPutStatistic(String.valueOf(Arrays.asList(monthNames).indexOf(month.getText().toString())), year.getText().toString());
                    }
                }
        );

        monthMore.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int month_temp = Arrays.asList(monthNames).indexOf(month.getText().toString());
                        month_temp += 1;
                        if(month_temp > 11) month_temp = 0;
                        month.setText(monthNames[month_temp]);

                        outPutTabLayout(positionTab);
                        outPutStatistic(String.valueOf(Arrays.asList(monthNames).indexOf(month.getText().toString())), year.getText().toString());
                    }
                }
        );
    }
}