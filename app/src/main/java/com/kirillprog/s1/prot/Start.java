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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class Start extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ListView listView_python = (ListView) findViewById(R.id.python_lesson);
        ListView listView_cpp = (ListView) findViewById(R.id.cpp_lesson);
        final String[] p_lesson = getResources().getStringArray(R.array.python_lesson_name);
        final String[] c_lesson = getResources().getStringArray(R.array.cpp_lesson_name);
        ArrayAdapter<String> pythonAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, p_lesson);
        ArrayAdapter<String> cppAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, c_lesson);

        listView_python.setAdapter(pythonAdapter);
        listView_cpp.setAdapter(cppAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TabHost tabHost = (TabHost) findViewById(R.id.lang);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("C++");

        tabSpec.setContent(R.id.cpp_lesson);
        tabSpec.setIndicator("C++");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("PYTHON 3");
        tabSpec.setContent(R.id.python_lesson);
        tabSpec.setIndicator("Python 3");
        tabHost.addTab(tabSpec);
        tabHost.setCurrentTab(0);

        listView_cpp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();
                int num;
                for(num = 0; num < 12; num++){
                    if(strText.equals(c_lesson[num])) break;
                }
                Intent intent = new Intent(Start.this, Testing.class);
                intent.putExtra("num", num);
                intent.putExtra("lang", tabHost.getCurrentTab());
                startActivity(intent);
            }
        });

        listView_python.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();
                int num;
                for(num = 0; num < 12; num++){
                    if(strText.equals(p_lesson[num])) break;
                }
                Intent intent = new Intent(Start.this, Testing.class);
                intent.putExtra("num", num);
                intent.putExtra("lang", tabHost.getCurrentTab());
                startActivity(intent);
            }
        });

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
        getMenuInflater().inflate(R.menu.start, menu);
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
            //Intent intent = new Intent(Start.this, Start.class);
            //startActivity(intent);
        } else if (id == R.id.nav_book) {
            Intent intent = new Intent(Start.this, Book.class);
            startActivity(intent);


        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(Start.this, Help.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(Start.this, Share.class);
            startActivity(intent);

        } else if (id == R.id.nav_creators) {
            Intent intent = new Intent(Start.this, Creators.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
