package com.kirillprog.s1.prot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Book extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String[] mGroupsArray1 = new String[] { "Зима", "Весна", "Лето", "Осень" };

    private String[] mWinterMonthsArray1 = new String[] { "Декабрь12"};
    private String[] mSpringMonthsArray1 = new String[] { "Март11"};
    private String[] mSummerMonthsArray1 = new String[] { "Июнь10"};
    private String[] mAutumnMonthsArray1 = new String[] { "Сентябрь9"};

    private String[] mGroupsArray2 = new String[] { "Синтаксис", "Ввод и вывод. Hello, World!", "Условия", "Арифметика. Типы. Math" };

    private String[] mWinterMonthsArray2 = new String[] {""};
    private String[] mSpringMonthsArray2 = new String[] { "Март1"};
    private String[] mSummerMonthsArray2 = new String[] { "Июнь2"};
    private String[] mAutumnMonthsArray2 = new String[] { "Сентябрь3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabHost tabHost = (TabHost) findViewById(R.id.book_list_1);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("C++");
        tabSpec.setContent(R.id.cpp_list);
        tabSpec.setIndicator("C++");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("PYTHON 3");
        tabSpec.setContent(R.id.python_list);
        tabSpec.setIndicator("Python 3");
        tabHost.addTab(tabSpec);
        tabHost.setCurrentTab(0);


        Map<String, String> map;
        // коллекция для групп
        ArrayList<Map<String, String>> groupDataList1 = new ArrayList<>();
        ArrayList<Map<String, String>> groupDataList2 = new ArrayList<>();
        // заполняем коллекцию групп из массива с названиями групп

        for (String group : mGroupsArray1) {
            // заполняем список атрибутов для каждой группы
            map = new HashMap<>();
            map.put("groupName", group); // время года
            groupDataList1.add(map);
        }
        for (String group : mGroupsArray2) {
            // заполняем список атрибутов для каждой группы
            map = new HashMap<>();
            map.put("groupName", group); // время года
            groupDataList2.add(map);
        }

        // список атрибутов групп для чтения
        String groupFrom1[] = new String[] { "groupName" };
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo1[] = new int[] { android.R.id.text1 };
        // список атрибутов групп для чтения
        String groupFrom2[] = new String[] { "groupName" };
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo2[] = new int[] { android.R.id.text1 };

        // создаем общую коллекцию для коллекций элементов
        ArrayList<ArrayList<Map<String, String>>> сhildDataList1 = new ArrayList<>();
        ArrayList<ArrayList<Map<String, String>>> сhildDataList2 = new ArrayList<>();

        // в итоге получится сhildDataList = ArrayList<сhildDataItemList>

        // создаем коллекцию элементов для первой группы
        ArrayList<Map<String, String>> сhildDataItemList1 = new ArrayList<>();
        ArrayList<Map<String, String>> сhildDataItemList2 = new ArrayList<>();
        // заполняем список атрибутов для каждого элемента
        for (String month : mWinterMonthsArray1) {
            map = new HashMap<>();
            map.put("monthName", month); // название месяца
            сhildDataItemList1.add(map);
        }
        сhildDataList1.add(сhildDataItemList1);
        for (String month : mWinterMonthsArray2) {
            map = new HashMap<>();
            map.put("monthName", month); // название месяца
            сhildDataItemList2.add(map);
        }
        // добавляем в коллекцию коллекций
        сhildDataList2.add(сhildDataItemList2);

        // создаем коллекцию элементов для второй группы
        сhildDataItemList1 = new ArrayList<>();
        for (String month : mSpringMonthsArray1) {
            map = new HashMap<>();
            map.put("monthName", month);
            сhildDataItemList1.add(map);
        }
        сhildDataList1.add(сhildDataItemList1);
        сhildDataItemList2 = new ArrayList<>();
        for (String month : mSpringMonthsArray2) {
            map = new HashMap<>();
            map.put("monthName", month);
            сhildDataItemList2.add(map);
        }
        сhildDataList2.add(сhildDataItemList2);

        // создаем коллекцию элементов для третьей группы
        сhildDataItemList1 = new ArrayList<>();
        for (String month : mSummerMonthsArray1) {
            map = new HashMap<>();
            map.put("monthName", month);
            сhildDataItemList1.add(map);
        }
        сhildDataList1.add(сhildDataItemList1);
        сhildDataItemList2 = new ArrayList<>();
        for (String month : mSummerMonthsArray2) {
            map = new HashMap<>();
            map.put("monthName", month);
            сhildDataItemList2.add(map);
        }
        сhildDataList2.add(сhildDataItemList2);

        // создаем коллекцию элементов для четвертой группы
        сhildDataItemList1 = new ArrayList<>();
        for (String month : mAutumnMonthsArray1) {
            map = new HashMap<>();
            map.put("monthName", month);
            сhildDataItemList1.add(map);
        }
        сhildDataList1.add(сhildDataItemList1);
        сhildDataItemList2 = new ArrayList<>();
        for (String month : mAutumnMonthsArray2) {
            map = new HashMap<>();
            map.put("monthName", month);
            сhildDataItemList2.add(map);
        }
        сhildDataList2.add(сhildDataItemList2);

        // список атрибутов элементов для чтения
        String childFrom1[] = new String[] { "monthName" };
        String childFrom2[] = new String[] { "monthName" };
        // список ID view-элементов, в которые будет помещены атрибуты
        // элементов
        int childTo1[] = new int[] { android.R.id.text1 };
        int childTo2[] = new int[] { android.R.id.text1 };

        SimpleExpandableListAdapter adapterCPP = new SimpleExpandableListAdapter(
                this, groupDataList1,
                android.R.layout.simple_expandable_list_item_1, groupFrom1,
                groupTo1, сhildDataList1, android.R.layout.simple_list_item_1,
                childFrom1, childTo1);
        SimpleExpandableListAdapter adapterPYTHON = new SimpleExpandableListAdapter(
                this, groupDataList2,
                android.R.layout.simple_expandable_list_item_1, groupFrom2,
                groupTo2, сhildDataList2, android.R.layout.simple_list_item_1,
                childFrom2, childTo2);

        ExpandableListView expandableListViewCPP = (ExpandableListView) findViewById(R.id.cpp_list);
        expandableListViewCPP.setAdapter(adapterCPP);
        ExpandableListView expandableListViewPYTHON = (ExpandableListView) findViewById(R.id.python_list);
        expandableListViewPYTHON.setAdapter(adapterPYTHON);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_start) {
            Intent intent = new Intent(Book.this, Start.class);
            startActivity(intent);
        } else if (id == R.id.nav_book) {
            //Intent intent = new Intent(Start.this, Book.class);
            //startActivity(intent);

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(Book.this, Settings.class);
            startActivity(intent);

        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(Book.this, Help.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(Book.this, Share.class);
            startActivity(intent);

        } else if (id == R.id.nav_creators) {
            Intent intent = new Intent(Book.this, Creators.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
