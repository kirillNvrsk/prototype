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

    private String[] cppLessonName;
    private String[] cppLessonText;

    private String[] pythonLessonName;
    private String[] pythonLessonText;


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


        cppLessonName = getResources().getStringArray(R.array.cpp_lesson_name);
        cppLessonText = getResources().getStringArray(R.array.cpp_book_str);

        pythonLessonName = getResources().getStringArray(R.array.python_lesson_name);
        pythonLessonText = getResources().getStringArray(R.array.python_lesson_name);


        Map<String, String> map;
        // коллекция для групп
        ArrayList<Map<String, String>> groupDataList1 = new ArrayList<>();
        ArrayList<Map<String, String>> groupDataList2 = new ArrayList<>();
        // заполняем коллекцию групп из массива с названиями групп

        for (String group : cppLessonName) {
            // заполняем список атрибутов для каждой группы
            map = new HashMap<>();
            map.put("groupName", group); // время года
            groupDataList1.add(map);
        }
        for (String group : pythonLessonName) {
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

        ArrayList<ArrayList<Map<String, String>>> сhildDataList1 = new ArrayList<>();
        ArrayList<ArrayList<Map<String, String>>> сhildDataList2 = new ArrayList<>();

        ArrayList<Map<String, String>> сhildDataItemList1 = new ArrayList<>();
        ArrayList<Map<String, String>> сhildDataItemList2 = new ArrayList<>();

        for(String lessons : cppLessonText){
            сhildDataItemList1 = new ArrayList<>();
            map = new HashMap<>();
            map.put("lessonName", lessons);
            сhildDataItemList1.add(map);
            сhildDataList1.add(сhildDataItemList1);

        }
        for(String lessons : pythonLessonText){
            сhildDataItemList2 = new ArrayList<>();
            map = new HashMap<>();
            map.put("lessonName", lessons);
            сhildDataItemList2.add(map);
            сhildDataList2.add(сhildDataItemList2);

        }
        // список атрибутов элементов для чтения
        String childFrom1[] = new String[] { "lessonName" };
        String childFrom2[] = new String[] { "lessonName" };
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
        getMenuInflater().inflate(R.menu.book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        int id = item.getItemId();

        if (id == R.id.nav_start) {
            Intent intent = new Intent(Book.this, Start.class);
            startActivity(intent);
        } else if (id == R.id.nav_book) {
            //Intent intent = new Intent(Start.this, Book.class);
            //startActivity(intent);


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
